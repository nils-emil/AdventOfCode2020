package exercises;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class Day8Test {

    @Test
    public void findMySeatId() throws FileNotFoundException {
        assertEquals(969, Day8.getAccumulationUntilLastLine("day8.csv"));
    }

    @Test
    public void findMySeatId_test() throws FileNotFoundException {
        assertEquals(8, Day8.getAccumulationUntilLastLine("day8_test.csv"));
    }

}
