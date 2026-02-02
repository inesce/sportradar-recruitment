package com.sportradar.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void shouldStartGameWithInitialScore() {
        Match match = scoreboard.startGame("Mexico", "Canada");

        assertNotNull(match);
        assertEquals("Mexico", match.getHomeTeam().getName());
        assertEquals("Canada", match.getAwayTeam().getName());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void shouldStartMultipleGames() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");

        List<Match> summary = scoreboard.getSummary();
        assertEquals(2, summary.size());
    }

    @Test
    void shouldThrowExceptionWhenStartingDuplicateGame() {
        scoreboard.startGame("Mexico", "Canada");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scoreboard.startGame("Mexico", "Canada")
        );
        assertEquals("Match between Mexico and Canada already exists", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenHomeTeamEqualsAwayTeam() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.startGame("Mexico", "Mexico")
        );
        assertEquals("Home team and away team must be different", exception.getMessage());
    }

    @Test
    void shouldRemoveGameWhenFinished() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.finishGame("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    void shouldRemoveCorrectGameWhenMultipleGamesExist() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");

        scoreboard.finishGame("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Spain", summary.get(0).getHomeTeam().getName());
    }

    @Test
    void shouldThrowExceptionWhenFinishingNonExistentGame() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scoreboard.finishGame("Mexico", "Canada")
        );
        assertEquals("Match between Mexico and Canada does not exist", exception.getMessage());
    }

    @Test
    void shouldUpdateGameScore() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);

        List<Match> summary = scoreboard.getSummary();
        assertEquals(0, summary.get(0).getHomeScore());
        assertEquals(5, summary.get(0).getAwayScore());
    }

    @Test
    void shouldUpdateScoreMultipleTimes() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        scoreboard.updateScore("Mexico", "Canada", 0, 2);
        scoreboard.updateScore("Mexico", "Canada", 0, 5);

        List<Match> summary = scoreboard.getSummary();
        assertEquals(0, summary.get(0).getHomeScore());
        assertEquals(5, summary.get(0).getAwayScore());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentGame() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> scoreboard.updateScore("Mexico", "Canada", 0, 5)
        );
        assertEquals("Match between Mexico and Canada does not exist", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNegativeScoresInUpdate() {
        scoreboard.startGame("Mexico", "Canada");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> scoreboard.updateScore("Mexico", "Canada", -1, 0)
        );
        assertEquals("Scores cannot be negative", exception.getMessage());
    }

    @Test
    void shouldReturnEmptySummaryWhenNoGames() {
        List<Match> summary = scoreboard.getSummary();

        assertNotNull(summary);
        assertTrue(summary.isEmpty());
    }

    @Test
    void shouldReturnSummaryOrderedByTotalScoreDescending() {
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);  // Total: 5
        scoreboard.updateScore("Spain", "Brazil", 10, 2);  // Total: 12

        List<Match> summary = scoreboard.getSummary();

        assertEquals(2, summary.size());
        assertEquals("Spain", summary.get(0).getHomeTeam().getName());
        assertEquals("Mexico", summary.get(1).getHomeTeam().getName());
    }

    @Test
    void gamesWithSameTotalScoreShouldBeOrderedByMostRecentlyAdded() {
        scoreboard.startGame("Mexico", "Canada");      // Added first
        scoreboard.startGame("Spain", "Brazil");       // Added second

        scoreboard.updateScore("Mexico", "Canada", 2, 3);  // Total: 5
        scoreboard.updateScore("Spain", "Brazil", 3, 2);   // Total: 5

        List<Match> summary = scoreboard.getSummary();

        // Spain-Brazil should come first (added more recently)
        assertEquals("Spain", summary.get(0).getHomeTeam().getName());
        assertEquals("Mexico", summary.get(1).getHomeTeam().getName());
    }
}