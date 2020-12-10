package day10;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class AdapterArrayTest {

    @Test
    public void getJoltMultiplication_case1() throws FileNotFoundException {
        assertEquals(2048, AdapterArray.getJoltDifferenceMultiplication("day10.csv"));
    }

    @Test
    public void getJoltMultiplication_case2() throws FileNotFoundException {
        assertEquals(35, AdapterArray.getJoltDifferenceMultiplication("day10_test.csv"));
    }

    @Test
    public void countNumberOfWays_case1() throws FileNotFoundException {
        assertEquals(1322306994176L, AdapterArray.countNumberOfWays("day10.csv"));
    }

    @Test
    public void countNumberOfWays_case2() throws FileNotFoundException {
        assertEquals(8L, AdapterArray.countNumberOfWays("day10_test.csv"));
    }
}
