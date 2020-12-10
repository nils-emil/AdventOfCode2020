package day6;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class CustomCustoms {

    @Test
    public void findMySeatId() throws FileNotFoundException {
        assertEquals(2947, CustomsCustoms.getSumOfCollectiveAnswers("day6.csv"));
    }

}
