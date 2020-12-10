package day3;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class TobogganTrajectory {


    public static final int NUMBER_OF_VERTICAL_INCREMENT = 3;

    public static int getNumberOfTreesEncounteredFromTopLeftToBottom(String inputfile) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(inputfile);
        int numberOfTrees = 0;
        int yIndex = 0;
        for (String row : input) {
            if ('#' == row.charAt(yIndex)) {
                numberOfTrees++;
            }
            yIndex += NUMBER_OF_VERTICAL_INCREMENT;
            yIndex = yIndex % row.length();
        }
        return numberOfTrees;
    }


    public static int getNumberOfTreesEncounteredFromTopLeftToBottom(String inputfile,
                                                                     int xIncrement,
                                                                     int yIncrement) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(inputfile);
        int numberOfTrees = 0;
        int xIndex = 0;
        int yIndex = 0;
        for (String row : input) {
            if (yIndex % yIncrement == 0) {
                if ('#' == row.charAt(xIndex)) {
                    numberOfTrees++;
                }
                xIndex += xIncrement;
                xIndex = xIndex % row.length();
            }
            yIndex ++;

        }
        return numberOfTrees;
    }

}
