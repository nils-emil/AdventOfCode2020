package exercises;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TobogganTrajectoryTest {


    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv");
        assertEquals(7, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_real() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv");
        assertEquals(232, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_Right1_Down1() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv", 1,1);
        assertEquals(2, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_Right3_Down1() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv", 3,1);
        assertEquals(7, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_Right5_Down1() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv", 5,1);
        assertEquals(3, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_Right7_Down1() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv", 7,1);
        assertEquals(4, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_Right1_Down2() throws FileNotFoundException {
        int numberOfTrees = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3.csv", 1,2);
        assertEquals(2, numberOfTrees);
    }

    @Test
    public void getNumberOfTreesEncounteredFromTopLeftToBottom_sum() throws FileNotFoundException {
        long num1 = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv", 1,1);
        long num2 = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv", 3,1);
        long num3 = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv", 5,1);
        long num4 = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv", 7,1);
        long num5 = TobogganTrajectory.getNumberOfTreesEncounteredFromTopLeftToBottom("day3_real.csv", 1,2);
        assertEquals(3952291680L, num1 * num2 * num3 * num4 * num5);
    }

}
