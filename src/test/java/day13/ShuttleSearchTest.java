package day13;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ShuttleSearchTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(295, ShuttleSearch.getEarliestBusToTake("day13_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        // not 1007158
        Assert.assertEquals(2165, ShuttleSearch.getEarliestBusToTake("day13.csv"));
    }

    @Test
    public void part2_case1() throws FileNotFoundException {
        Assert.assertEquals(1068781, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test.csv"));
    }

    @Test
    public void part2_case2() throws FileNotFoundException {
        Assert.assertEquals(3417, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test2.csv"));
    }

    @Test
    public void part2_case3() throws FileNotFoundException {
        Assert.assertEquals(754018, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test3.csv"));
    }

    @Test
    public void part2_case4() throws FileNotFoundException {
        Assert.assertEquals(779210, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test4.csv"));
    }

    @Test
    public void part2_case5() throws FileNotFoundException {
        Assert.assertEquals(1261476, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test5.csv"));
    }

    @Test
    public void part2_case6() throws FileNotFoundException {
        Assert.assertEquals(1202161486, ShuttleSearch.getEarliestSbsequentTimeStamp("day13_test6.csv"));
    }

    @Test
    public void part2_case7() throws FileNotFoundException {
        Assert.assertEquals(1202161486, ShuttleSearch.getEarliestSbsequentTimeStamp("day13.csv"));
    }
}
