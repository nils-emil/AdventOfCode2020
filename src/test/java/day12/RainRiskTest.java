package day12;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class RainRiskTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(25, RainRisk.getManhattanDistance("day12_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        assertEquals(1589, RainRisk.getManhattanDistance("day12.csv"));
    }
}
