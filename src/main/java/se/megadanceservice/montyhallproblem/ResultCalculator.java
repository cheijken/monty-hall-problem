package se.megadanceservice.montyhallproblem;

class ResultCalculator {

    double calculateWinLossDeviation(int count, int wins) {
        double balance = (double) Math.abs(2*wins - count) / 2;
        return balance / (double) count;
    }

}
