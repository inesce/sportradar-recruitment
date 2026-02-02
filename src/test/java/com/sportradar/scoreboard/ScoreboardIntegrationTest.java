package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test that verifies the complete workflow matches the requirements example.
 */
class ScoreboardIntegrationTest {

    @Test
    void shouldMatchExampleFromRequirements() {
        Scoreboard scoreboard = new Scoreboard();

        // Start games in order as specified in requirements
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");
        scoreboard.startGame("Germany", "France");
        scoreboard.startGame("Uruguay", "Italy");
        scoreboard.startGame("Argentina", "Australia");

        // Update scores as specified in requirements
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = scoreboard.getSummary();

        // Expected order from requirements:
        // 1. Uruguay 6 - Italy 6 (Total: 12, most recent among 12s)
        // 2. Spain 10 - Brazil 2 (Total: 12, added before Uruguay)
        // 3. Mexico 0 - Canada 5 (Total: 5)
        // 4. Argentina 3 - Australia 1 (Total: 4, more recent than Germany)
        // 5. Germany 2 - France 2 (Total: 4)

        assertEquals(5, summary.size());

        assertMatchEquals(summary.get(0), "Uruguay", "Italy", 6, 6);
        assertMatchEquals(summary.get(1), "Spain", "Brazil", 10, 2);
        assertMatchEquals(summary.get(2), "Mexico", "Canada", 0, 5);
        assertMatchEquals(summary.get(3), "Argentina", "Australia", 3, 1);
        assertMatchEquals(summary.get(4), "Germany", "France", 2, 2);
    }

    @Test
    void shouldHandleGameLifecycle() {
        Scoreboard scoreboard = new Scoreboard();

        // Start games
        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");
        scoreboard.startGame("Germany", "France");

        // Update scores
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);

        // Finish one game
        scoreboard.finishGame("Germany", "France");

        List<Match> summary = scoreboard.getSummary();

        // Should only have 2 games now
        assertEquals(2, summary.size());
        assertMatchEquals(summary.get(0), "Spain", "Brazil", 10, 2);
        assertMatchEquals(summary.get(1), "Mexico", "Canada", 0, 5);
    }

    @Test
    void shouldHandleAllGamesWithZeroScores() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startGame("Mexico", "Canada");
        scoreboard.startGame("Spain", "Brazil");
        scoreboard.startGame("Germany", "France");

        List<Match> summary = scoreboard.getSummary();

        // All have score 0-0, so ordered by most recently added
        assertEquals(3, summary.size());
        assertMatchEquals(summary.get(0), "Germany", "France", 0, 0);
        assertMatchEquals(summary.get(1), "Spain", "Brazil", 0, 0);
        assertMatchEquals(summary.get(2), "Mexico", "Canada", 0, 0);
    }

    @Test
    void shouldHandleSingleGame() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startGame("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 3, 2);

        List<Match> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());
        assertMatchEquals(summary.get(0), "Mexico", "Canada", 3, 2);
    }

    private void assertMatchEquals(Match match, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        assertEquals(homeTeam, match.getHomeTeam().getName(),
                String.format("Expected home team %s but got %s", homeTeam, match.getHomeTeam().getName()));
        assertEquals(awayTeam, match.getAwayTeam().getName(),
                String.format("Expected away team %s but got %s", awayTeam, match.getAwayTeam().getName()));
        assertEquals(homeScore, match.getHomeScore(),
                String.format("Expected home score %d but got %d for %s vs %s", homeScore, match.getHomeScore(), homeTeam, awayTeam));
        assertEquals(awayScore, match.getAwayScore(),
                String.format("Expected away score %d but got %d for %s vs %s", awayScore, match.getAwayScore(), homeTeam, awayTeam));
    }
}
