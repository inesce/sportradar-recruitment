package com.sportradar.scoreboard;

import java.util.Objects;

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
     * @throws IllegalArgumentException if name is null, empty or blank
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}