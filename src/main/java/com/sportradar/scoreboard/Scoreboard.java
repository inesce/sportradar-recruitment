package com.sportradar.scoreboard;

import java.util.*;

/**
 * Scoreboard for managing live football matches.
 * Supports starting games.
 */
public class Scoreboard {
    private final Map<MatchKey, Match> matches;
    private int orderCounter;

    /**
     * Creates a new empty scoreboard.
     */
    public Scoreboard() {
        this.matches = new LinkedHashMap<>();
        this.orderCounter = 0;
    }

    /**
     * Starts a new game with initial score 0-0.
     *
     * @param homeTeamName the home team name
     * @param awayTeamName the away team name
     * @return the created match
     * @throws IllegalArgumentException if team names are invalid or teams are the same
     * @throws IllegalStateException if a match between these teams already exists
     */
    public Match startGame(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home team and away team must be different");
        }

        MatchKey key = new MatchKey(homeTeam, awayTeam);

        if (matches.containsKey(key)) {
            throw new IllegalStateException(
                    String.format("Match between %s and %s already exists", homeTeamName, awayTeamName)
            );
        }

        int order = orderCounter++;
        Match match = new Match(homeTeam, awayTeam, order);
        matches.put(key, match);

        return match;
    }

    /**
     * Finishes a game and removes it from the scoreboard.
     *
     * @param homeTeamName the home team name
     * @param awayTeamName the away team name
     * @throws IllegalArgumentException if team names are invalid
     * @throws IllegalStateException if the match does not exist
     */
    public void finishGame(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        MatchKey key = new MatchKey(homeTeam, awayTeam);

        if (!matches.containsKey(key)) {
            throw new IllegalStateException(
                    String.format("Match between %s and %s does not exist", homeTeamName, awayTeamName)
            );
        }

        matches.remove(key);
    }

    /**
     * Returns a list of all matches
     *
     * @return list of matches
     */
    public List<Match> getSummary() {
        return new ArrayList<>(matches.values());  // no sorting yet
    }

    /**
     * Internal key class for identifying matches by team pair.
     */
    private static class MatchKey {
        private final Team homeTeam;
        private final Team awayTeam;

        MatchKey(Team homeTeam, Team awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MatchKey matchKey = (MatchKey) o;
            return Objects.equals(homeTeam, matchKey.homeTeam) &&
                    Objects.equals(awayTeam, matchKey.awayTeam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(homeTeam, awayTeam);
        }
    }
}