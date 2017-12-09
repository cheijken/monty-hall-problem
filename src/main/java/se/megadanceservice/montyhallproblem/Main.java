package se.megadanceservice.montyhallproblem;

public class Main {

    private static final int GAME_COUNT = 100000000;
    private static final int TEST_PRECISION = 5;

    public static void main(String[] params) {
        System.out.println("Assume that you are attending a TV show where you can win\n" +
                           "money by picking the right box. The game show host shows you\n" +
                           "three boxes explaining that the money is in one of the boxes.\n" +
                           "He asks you to pick one of them without opening it. After you\n" +
                           "have picked one of the boxes he opens one of the other two\n" +
                           "boxes which is empty. Now he turns to you and asks, do you\n" +
                           "want to change your mind, picking the remaining box?\n");

        GamesSimulator gamesSimulator = new GamesSimulator(new ResultCalculator(), new Randomize(), TEST_PRECISION);

        System.out.println("Testing the game "+GAME_COUNT+" times.");
        System.out.println("Calculating ...\n");

        final boolean doIStandABetterChanceToWinIfIChangeMyMind =
                gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(GAME_COUNT);

        System.out.println("Do I stand a better chance to win if I change my mind?: "
                                   + (doIStandABetterChanceToWinIfIChangeMyMind ? "yes": "no"));
    }
}
