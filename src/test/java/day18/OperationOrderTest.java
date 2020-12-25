package day18;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class OperationOrderTest {


    @Test
    public void part1_case1() throws FileNotFoundException {
        Assert.assertEquals(71, OperationOrder.getCalulcatonResult("1 + 2 * 3 + 4 * 5 + 6"));
    }

    @Test
    public void part1_case2() throws FileNotFoundException {
        Assert.assertEquals(51, OperationOrder.getCalulcatonResult("1 + (2 * 3) + (4 * (5 + 6))"));
    }


}
