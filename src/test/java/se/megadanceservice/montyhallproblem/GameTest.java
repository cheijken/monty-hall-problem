package se.megadanceservice.montyhallproblem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private Game game;
    private Boxes boxes;
    private final Box winningBox = () -> true;
    private final Box looseBox = () -> false;

    @Before public void setup() {
        boxes = mock(Boxes.class);
        game = new Game(boxes);
    }

    @Test public void testChangeAndLoose() {
        //Given
        when(boxes.changeSelection(any(Box.class))).thenReturn(looseBox);

        //When
        game.makeChoice(Choice.CHANGE_MIND);

        //Then
        assertEquals(Result.Loose, game.result());

    }

    @Test public void testChangeAndWin() {
        //Given
        when(boxes.changeSelection(any(Box.class))).thenReturn(winningBox);

        //When
        game.makeChoice(Choice.CHANGE_MIND);

        //Then
        assertEquals(Result.Win, game.result());

    }

    @Test public void testStartWinnerNoChangeAndWin() {
        //Given
        when(boxes.getBox(anyInt())).thenReturn(winningBox);

        //When
        game.selectBox(1);
        game.makeChoice(Choice.DO_NOT_CHANGE_MIND);

        //Then
        assertEquals(Result.Win, game.result());

    }

    @Test public void testStartLooserNoChangeAndLoose() {
        //Given
        when(boxes.getBox(anyInt())).thenReturn(looseBox);

        //When
        game.selectBox(1);
        game.makeChoice(Choice.DO_NOT_CHANGE_MIND);

        //Then
        assertEquals(Result.Loose, game.result());

    }

}