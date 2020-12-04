package exercises;


import org.junit.Test;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class PasswordValidatorTest {


    @Test
    public void hasExactlyOneCharacterAtSpecifiedPositions_lotsOfNames() throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLinesAsUniqueNames("day2.csv");
        int counter = 0;
        for (String string: input)  {
            boolean b = PasswordValidator.hasValidAmountOfRequiredCharacters(string);
            if (b) {
                counter++;
            }
        }
        assertEquals(519, counter);
    }

    @Test
    public void hasExactlyOneCharacterAtSpecifiedPositions_case1() {
        boolean b = PasswordValidator.hasExactlyOneCharacterAtSpecifiedPositions("1-3 a: abcde");
        assertTrue(b);
    }

    @Test
    public void hasExactlyOneCharacterAtSpecifiedPositions_case2() {
        boolean b = PasswordValidator.hasExactlyOneCharacterAtSpecifiedPositions("1-3 b: cdefg");
        assertFalse(b);
    }

    @Test
    public void hasExactlyOneCharacterAtSpecifiedPositions_case3() {
        boolean b = PasswordValidator.hasExactlyOneCharacterAtSpecifiedPositions("2-9 c: ccccccccc");
        assertFalse(b);
    }
}
