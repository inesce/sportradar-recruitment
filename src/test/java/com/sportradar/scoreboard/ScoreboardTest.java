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
}