package day8;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class HandheldHaltingTest {

    @Test
    public void getAccumulationUntilLastLine_case1() throws FileNotFoundException {
        assertEquals(969, HandheldHalting.getAccumulationUntilLastLine("day8.csv"));
    }

    @Test
    public void getAccumulationUntilLastLine_case2() throws FileNotFoundException {
        assertEquals(8, HandheldHalting.getAccumulationUntilLastLine("day8_test.csv"));
    }

}
