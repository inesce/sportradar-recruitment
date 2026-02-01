package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void shouldCreateTeamWithValidName() {
        Team team = new Team("Mexico");
        assertEquals("Mexico", team.getName());
    }

    @Test
    void shouldThrowExceptionForNullTeamName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Team(null)
        );
        assertEquals("Team name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyTeamName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Team("")
        );
        assertEquals("Team name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForBlankTeamName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Team("   ")
        );
        assertEquals("Team name cannot be null or empty", exception.getMessage());
    }

    @Test
    void teamsWithSameNameShouldBeEqual() {
        Team team1 = new Team("Mexico");
        Team team2 = new Team("Mexico");
        assertEquals(team1, team2);
    }

    @Test
    void teamsWithSameNameShouldHaveSameHashCode() {
        Team team1 = new Team("Mexico");
        Team team2 = new Team("Mexico");
        assertEquals(team1.hashCode(), team2.hashCode());
    }

    @Test
    void teamsWithDifferentNamesShouldNotBeEqual() {
        Team team1 = new Team("Mexico");
        Team team2 = new Team("Canada");
        assertNotEquals(team1, team2);
    }

    @Test
    void teamNamesShouldBeCaseSensitive() {
        Team team1 = new Team("Mexico");
        Team team2 = new Team("mexico");
        assertNotEquals(team1, team2);
    }
}