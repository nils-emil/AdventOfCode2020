package day07;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class HandyHaversacksTest {

    @Test
    public void howManyColorsCanHoldGoldenBag_case1() throws FileNotFoundException {
        assertEquals(229, HandyHaversacks.howManyColorsCanHoldGoldenBag("day7.csv"));
    }

    @Test
    public void howManyColorsCanHoldGoldenBag_case2() throws FileNotFoundException {
        assertEquals(4, HandyHaversacks.howManyColorsCanHoldGoldenBag("day7_test.csv"));
    }

    @Test
    public void howManyIndividualBagsInsideGoldenBag_case1() throws FileNotFoundException {
        assertEquals(6683, HandyHaversacks.howManyIndividualBagsInsideGoldenBag("day7.csv"));
    }

    @Test
    public void howManyIndividualBagsInsideGoldenBag_case2() throws FileNotFoundException {
        assertEquals(32, HandyHaversacks.howManyIndividualBagsInsideGoldenBag("day7_test.csv"));
    }

    @Test
    public void howManyIndividualBagsInsideGoldenBag_case2_case3() throws FileNotFoundException {
        assertEquals(126, HandyHaversacks.howManyIndividualBagsInsideGoldenBag("day7_test2.csv"));
    }

}
