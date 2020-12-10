package day01;

import org.junit.Test;

import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

public class ExpenseReportTest {

    @Test
    public void getMultiplicationOfTwoNumbersThatSumTo2020_example() throws FileNotFoundException {
        assertEquals(514579, ExpenseReport.getMultiplicationOfTwoNumbersThatSumTo2020("day1.csv"));
    }

    @Test
    public void getMultiplicationOfTwoNumbersThatSumTo2020_real() throws FileNotFoundException {
        assertEquals(482811, ExpenseReport.getMultiplicationOfTwoNumbersThatSumTo2020("day1_real.csv"));
    }

    @Test
    public void getMultiplicationOfThreeNumbersThatSumTo2020_example() throws FileNotFoundException {
        assertEquals(241861950, ExpenseReport.getMultiplicationOfThreeNumbersThatSumTo2020("day1.csv"));
    }

    @Test
    public void getMultiplicationOfThreeNumbersThatSumTo2020_real() throws FileNotFoundException {
        assertEquals(193171814, ExpenseReport.getMultiplicationOfThreeNumbersThatSumTo2020("day1_real.csv"));
    }


}
