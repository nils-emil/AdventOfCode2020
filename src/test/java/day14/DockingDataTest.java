package day14;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class DockingDataTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(51, DockingData.getMemorySum("day14_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        Assert.assertEquals(10035335144067L, DockingData.getMemorySum("day14.csv"));
    }

}
