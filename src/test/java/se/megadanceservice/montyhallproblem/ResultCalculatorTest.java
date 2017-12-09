package se.megadanceservice.montyhallproblem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultCalculatorTest {
    @Test
    public void calculateWinLossBalancePrecision() throws Exception {
        ResultCalculator resultCalculator = new ResultCalculator();

        assertEquals(0, resultCalculator.calculateWinLossDeviation(100, 50), 1);
        assertEquals(0.01, resultCalculator.calculateWinLossDeviation(100, 51), 1);
        assertEquals(0.01, resultCalculator.calculateWinLossDeviation(100, 49), 1);
        assertEquals(0.1, resultCalculator.calculateWinLossDeviation(100, 40), 1);
        assertEquals(0.1, resultCalculator.calculateWinLossDeviation(100, 60), 1);
        assertEquals(0.4, resultCalculator.calculateWinLossDeviation(100, 10), 1);
    }

}