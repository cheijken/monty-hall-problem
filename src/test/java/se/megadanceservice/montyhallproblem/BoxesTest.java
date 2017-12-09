package se.megadanceservice.montyhallproblem;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoxesTest {

    @Test public void getBox() throws Exception {
        Boxes boxes = new Boxes(1);

        assertTrue(boxes.getBox(1).winner());
        assertFalse(boxes.getBox(2).winner());
    }

    @Test public void changeSelection() throws Exception {
        Boxes boxes = new Boxes(1);

        assertTrue(boxes.changeSelection(() -> false).winner());
        assertFalse(boxes.changeSelection(() -> true).winner());
    }

}