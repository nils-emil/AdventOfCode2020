package exercises;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class TobogganTrajectory {


    public static final int NUMBER_OF_VERTICAL_INCREMENT = 3;

    public static int getNumberOfTreesEncounteredFromTopLeftToBottom(String inputfile) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLinesAsUniqueNames(inputfile);
        int numberOfTrees = 0;
        int currentIndex = 0;
        for (String row : input) {
            if ("#".charAt(0) == row.charAt(currentIndex)) {
                numberOfTrees++;
            }
            currentIndex += NUMBER_OF_VERTICAL_INCREMENT;
            currentIndex = currentIndex % row.length();
        }
        return numberOfTrees;
    }


    public static int getNumberOfTreesEncounteredFromTopLeftToBottom(String inputfile, int xInceremtn, int yIncrement) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLinesAsUniqueNames(inputfile);
        int numberOfTrees = 0;
        int currentIndex = 0;
        int yIndex = 0;
        for (String row : input) {
            if (yIndex % yIncrement == 0) {
                if ("#".charAt(0) == row.charAt(currentIndex)) {
                    numberOfTrees++;
                }
                currentIndex += xInceremtn;
                currentIndex = currentIndex % row.length();
            }
            yIndex ++;

        }
        return numberOfTrees;
    }

}
