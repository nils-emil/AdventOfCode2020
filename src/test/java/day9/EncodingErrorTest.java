package day9;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class EncodingErrorTest {

    @Test
    public void findEncryptionWeakness() throws FileNotFoundException {
        assertEquals(238243506, EncodingError.findEncryptionWeakness("day9.csv"));
    }

}
