package com.sportradar.scoreboard;

/**
 * Value object representing a football team.
 * Team names are immutable and validated upon creation.
 */
public final class Team {
    private final String name;

    /**
     * Creates a new Team with the given name.
     *
     * @param name the team name
     * @throws IllegalArgumentException if name is null or empty
     */
    public Team(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the team name.
     *
     * @return the team name
     */
    public String getName() {
        return name;
    }
}