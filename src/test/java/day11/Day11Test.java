package day11;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void task1_case1() throws FileNotFoundException {
        assertEquals(2048, Day11.task1("day11_test.csv"));
    }

    @Test
    public void task1_case2() throws FileNotFoundException {
        assertEquals(2048, Day11.task1("day11.csv"));
    }

}
