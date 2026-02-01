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
}