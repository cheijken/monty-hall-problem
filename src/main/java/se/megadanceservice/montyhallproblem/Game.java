package se.megadanceservice.montyhallproblem;

class Game {

    private final Boxes boxes;
    private Box selectedBox;

    Game(Boxes boxes) {
        this.boxes = boxes;
    }

    void selectBox(int selectedBoxNumber) {
        this.selectedBox = this.boxes.getBox(selectedBoxNumber);
    }

    void makeChoice(Choice choice) {
        if (Choice.CHANGE_MIND.equals(choice)) {
            selectedBox = boxes.changeSelection(selectedBox);
        }
    }

    Result result() {
        return selectedBox.winner() ? Result.Win : Result.Loose;
    }
}
