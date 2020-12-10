package day05;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class BinaryBoardingTest {

    @Test
    public void findMySeatId() throws FileNotFoundException {
        assertEquals(661, BinaryBoarding.findMySeatId("day5.csv"));
    }

}
