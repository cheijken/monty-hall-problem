package se.megadanceservice.montyhallproblem;

class Boxes {
    private final int winningBoxNumber;

    Boxes(int winningBoxNumber) {
        this.winningBoxNumber = winningBoxNumber;
    }

    Box getBox(int selectedBoxNumber) {
        return () -> winningBoxNumber == selectedBoxNumber;
    }

    Box changeSelection(Box selectedBox) {
        return () -> !selectedBox.winner();
    }
}
