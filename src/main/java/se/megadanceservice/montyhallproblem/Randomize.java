package se.megadanceservice.montyhallproblem;

import java.util.Random;

class Randomize {
    private final Random random;

    Randomize() {
        random = new Random();
    }

    int randomWinningBoxNumber() {
        return random.ints(1, 4).findFirst().getAsInt();
    }

    int randomSelectedBoxNumber() {
        return random.ints(1, 4).findFirst().getAsInt();
    }

    Choice randomChoice() {
        return random.nextBoolean() ? Choice.DO_NOT_CHANGE_MIND : Choice.CHANGE_MIND;
    }
}
