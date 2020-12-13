package day13;

import day12.RainRisk;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class ShuttleSearchTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(25, ShuttleSearch.getManhattanDistance("day13_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        // not 1007158
        assertEquals(2165, ShuttleSearch.getManhattanDistance("day13.csv"));
    }
}
