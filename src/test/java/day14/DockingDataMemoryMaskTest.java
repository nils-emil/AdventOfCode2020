package day14;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class DockingDataMemoryMaskTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(208, DockingDataMemoryMask.getMemorySum("day14_test.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        Assert.assertEquals(3817372618036L, DockingDataMemoryMask.getMemorySum("day14.csv"));
    }

}
