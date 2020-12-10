package day4;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class PassportProcessingTest {

    @Test
    public void getNumberOfValidPassports() throws FileNotFoundException {
        long validPassports = PassportProcessing.getNumberOfValidPassports("day4.csv");
        assertEquals(127, validPassports);
    }

    @Test
    public void getNumberOfValidPassports_test() throws FileNotFoundException {
        long validPassports = PassportProcessing.getNumberOfValidPassports("day4_test.csv");
        assertEquals(4, validPassports);
    }

    @Test
    public void regex() {
        assertFalse(Pattern.compile("^#([a-z-0-9]{6})$").matcher("#733820x").find());
        assertTrue(Pattern.compile("^#([a-z-0-9]{6})$").matcher("#733820").find());
    }

}
