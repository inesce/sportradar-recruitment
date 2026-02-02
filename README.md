# Football World Cup Score Board

A simple library for managing live football match scores.

## How to Run

```bash
# Run tests
./gradlew test

# Build
./gradlew build
```

## Usage

```java
Scoreboard scoreboard = new Scoreboard();

// Start a game (initial score 0-0)
scoreboard.startGame("Mexico", "Canada");

// Update score
scoreboard.updateScore("Mexico", "Canada", 0, 5);

// Get summary (sorted by total score, then by most recently added)
List<Match> summary = scoreboard.getSummary();

// Finish a game
scoreboard.finishGame("Mexico", "Canada");
```

## Assumptions

1. Team names are case-sensitive ("Mexico" ≠ "mexico")
2. A team pairing can only have one active match at a time
3. Scores must be non-negative integers
4. "Most recently added" refers to when the game was started, not last updated
5. Thread safety is not implemented (single-threaded usage assumed)
6. All data is in-memory only
