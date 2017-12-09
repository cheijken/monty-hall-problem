package se.megadanceservice.montyhallproblem;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class GamesSimulator {

    private final ResultCalculator resultCalculator;
    private final Randomize randomize;
    private final int precisionDigits;

    GamesSimulator(ResultCalculator resultCalculator, Randomize randomize, int precisionDigits) {
        this.resultCalculator = resultCalculator;
        this.randomize = randomize;
        this.precisionDigits = precisionDigits;
    }

    boolean doIStandABetterChanceToWinIfIChangeMyMind(int count) {
        int countWins = playGamesAndSumWinners(count);

        final double winLossBalancePrecision = resultCalculator.calculateWinLossDeviation(count, countWins);
        
        return isDiffBetweenWinAndLossSmallEnough(winLossBalancePrecision);
    }

    private boolean isDiffBetweenWinAndLossSmallEnough(double winLossBalancePrecision) {
        return winLossBalancePrecision < Math.pow(10,-precisionDigits);
    }

    private int playGamesAndSumWinners(int count) {
        return playGames(count)
                    .mapToInt(result -> Result.Win.equals(result) ? 1 : 0)
                    .sum();
    }

    private Stream<Result> playGames(int count) {
        return IntStream.range(0, count).boxed()
                .map(operand -> playGame());
    }

    private Result playGame() {
        Boxes boxes = new Boxes(randomize.randomWinningBoxNumber());
        Game game = new Game(boxes);
        game.selectBox(randomize.randomSelectedBoxNumber());
        game.makeChoice(randomize.randomChoice());
        return game.result();
    }
}
