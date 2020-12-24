package day16;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TicketTranslationTest {


    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(71, TicketTranslation.getSum("day16_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        Assert.assertEquals(21081, TicketTranslation.getSum("day16.csv"));
    }

    @Test
    public void part2_case1() throws FileNotFoundException {
        Assert.assertEquals(71, TicketTranslationPart2.getMultiplicationResult("day16_test_part2.csv"));
    }

    @Test
    public void part2_case2() throws FileNotFoundException {
        Assert.assertEquals(314360510573L, TicketTranslationPart2.getMultiplicationResult("day16.csv"));
    }

}
