package se.megadanceservice.montyhallproblem;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GamesSimulatorTest {

    private Randomize randomize;
    private GamesSimulator gamesSimulator;
    private ResultCalculator resultCalculator;

    @Before
    public void setup() {
        randomize = mock(Randomize.class);
        resultCalculator = mock(ResultCalculator.class);
        gamesSimulator = new GamesSimulator(resultCalculator, randomize, 2);
    }

    @Test
    public void winningPrecisionOk() throws Exception {
        //Given
        when(resultCalculator.calculateWinLossDeviation(anyInt(), anyInt())).thenReturn(0.001);

        //When
        final boolean result = gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(1);

        //Then
        assertTrue(result);
    }

    @Test
    public void winningPrecisionFail() throws Exception {
        //Given
        when(resultCalculator.calculateWinLossDeviation(anyInt(), anyInt())).thenReturn(0.01);

        //When
        final boolean result = gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(1);

        //Then
        assertFalse(result);
    }

    @Test
    public void fiftyPercentWinningAndNoSwitch() throws Exception {
        //Given
        when(randomize.randomWinningBoxNumber()).thenReturn(1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2);
        when(randomize.randomSelectedBoxNumber()).thenReturn(1);
        when(randomize.randomChoice()).thenReturn(Choice.DO_NOT_CHANGE_MIND);

        //When
        gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(20);

        //Then
        verify(resultCalculator, times(1)).calculateWinLossDeviation(eq(20), eq(10));
    }

    @Test
    public void selectedLoosingBoxAndNotSelectedNoSwitch() throws Exception {
        //Given
        when(randomize.randomWinningBoxNumber()).thenReturn(1);
        when(randomize.randomSelectedBoxNumber()).thenReturn(2);
        when(randomize.randomChoice()).thenReturn(Choice.DO_NOT_CHANGE_MIND);

        //When
        gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(20);

        //Then
        verify(resultCalculator, times(1)).calculateWinLossDeviation(eq(20), eq(0));
    }

    @Test
    public void selectedWinningBoxAndSwitch() throws Exception {
        //Given
        when(randomize.randomWinningBoxNumber()).thenReturn(1);
        when(randomize.randomSelectedBoxNumber()).thenReturn(1);
        when(randomize.randomChoice()).thenReturn(Choice.CHANGE_MIND);

        //When
        gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(20);

        //Then
        verify(resultCalculator, times(1)).calculateWinLossDeviation(eq(20), eq(0));
    }

    @Test
    public void fiftyPercentWinningAndSwitch() throws Exception {
        //Given
        when(randomize.randomWinningBoxNumber()).thenReturn(1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2);
        when(randomize.randomSelectedBoxNumber()).thenReturn(2);
        when(randomize.randomChoice()).thenReturn(Choice.CHANGE_MIND);

        //When
        gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(20);

        //Then
        verify(resultCalculator, times(1)).calculateWinLossDeviation(eq(20), eq(15));
    }

    @Test
    public void mostlyWinningsWinningAndAndNoSwitch() throws Exception {
        //Given
        when(randomize.randomWinningBoxNumber()).thenReturn(1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2);
        when(randomize.randomSelectedBoxNumber()).thenReturn(1);
        when(randomize.randomChoice()).thenReturn(Choice.DO_NOT_CHANGE_MIND);

        //When
        gamesSimulator.doIStandABetterChanceToWinIfIChangeMyMind(20);

        //Then
        verify(resultCalculator, times(1)).calculateWinLossDeviation(eq(20), eq(15));
    }
}