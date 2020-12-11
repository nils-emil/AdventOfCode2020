package day11;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class SeatingSystemTest {

    @Test
    public void task1_case1() throws FileNotFoundException {
        assertEquals(26, SeatingSystem.getNumberOfOccupiedSeats("day11_test.csv"));
    }

    @Test
    public void task1_case2() throws FileNotFoundException {
        assertEquals(1990, SeatingSystem.getNumberOfOccupiedSeats("day11.csv"));
    }

}
