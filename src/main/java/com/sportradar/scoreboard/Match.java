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
     * Updates the score of the match.
     *
     * @param homeScore the new home team score
     * @param awayScore the new away team score
     * @throws IllegalArgumentException if either score is negative
     */
    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
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

    /**
     * Returns the total score (sum of home and away scores).
     *
     * @return the total score
     */
    public int getTotalScore() {
        return homeScore + awayScore;
    }

    /**
     * Returns the creation order of this match.
     *
     * @return the creation order
     */
    public long getCreationOrder() {
        return creationOrder;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}