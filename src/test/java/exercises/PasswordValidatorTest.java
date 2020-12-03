package exercises;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordValidatorTest {


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
