package com.sportradar.scoreboard;

/**
 * Entity representing a football match between two teams.
 * A match maintains the current score and tracks when it was created for ordering purposes.
 */
public class Match {
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeScore;
    private int awayScore;
    private final long creationOrder;

    /**
     * Creates a new match with initial score of 0-0.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @param creationOrder the order in which this match was created
     * @throws IllegalArgumentException if either team is null
     */
    public Match(Team homeTeam, Team awayTeam, long creationOrder) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Home team and away team cannot be null");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.creationOrder = creationOrder;
    }

    /**
     * Returns the home team.
     *
     * @return the home team
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Returns the away team.
     *
     * @return the away team
     */
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * Returns the current home team score.
     *
     * @return the home team score
     */
    public int getHomeScore() {
        return homeScore;
    }

    /**
     * Returns the current away team score.
     *
     * @return the away team score
     */
    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}