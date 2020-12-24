package day17;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ConwayCubesTest {

    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(71, ConwayCubes.getSum("day17.csv"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        Assert.assertEquals(71, ConwayCubes.getSum("day17_test.csv"));
    }

}
