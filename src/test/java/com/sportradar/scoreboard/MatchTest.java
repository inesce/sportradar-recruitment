package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void shouldCreateMatchWithInitialScoreZeroZero() {
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match match = new Match(homeTeam, awayTeam, 1);

        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
    }

    @Test
    void shouldThrowExceptionForNullHomeTeam() {
        Team awayTeam = new Team("Canada");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Match(null, awayTeam, 1)
        );
        assertEquals("Home team and away team cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullAwayTeam() {
        Team homeTeam = new Team("Mexico");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Match(homeTeam, null, 1)
        );
        assertEquals("Home team and away team cannot be null", exception.getMessage());
    }

    @Test
    void shouldUpdateScore() {
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match match = new Match(homeTeam, awayTeam, 1);

        match.updateScore(2, 3);

        assertEquals(2, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

    @Test
    void shouldCalculateTotalScore() {
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match match = new Match(homeTeam, awayTeam, 1);

        match.updateScore(2, 3);

        assertEquals(5, match.getTotalScore());
    }

    @Test
    void shouldRejectNegativeHomeScore() {
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match match = new Match(homeTeam, awayTeam, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> match.updateScore(-1, 0)
        );
        assertEquals("Scores cannot be negative", exception.getMessage());
    }

    @Test
    void shouldRejectNegativeAwayScore() {
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match match = new Match(homeTeam, awayTeam, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> match.updateScore(0, -1)
        );
        assertEquals("Scores cannot be negative", exception.getMessage());
    }
}