package exercises;


import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HandheldHalting {

    public static int getAccumulationUntilLastLine(String inputfile) throws FileNotFoundException {
        List<String> initialCommands = FileUtils.getCsvLines(inputfile);
        List<Integer> infinityLoopIndexes = new ArrayList<>();
        List<String> lines = getCommandWithModification(initialCommands, infinityLoopIndexes);
        int iterations = 0;
        int accumulation = 0;
        int nextIndexToBeRun = 0;
        while (true) {
            iterations++;
            Pair<String, Integer> command = getCommand(lines.get(nextIndexToBeRun));
            nextIndexToBeRun = getNextIndexToBeRun(nextIndexToBeRun, command.getKey(), command.getValue());
            accumulation = getAccumulation(accumulation, command);
            if (iterations > 500) {
                lines = getCommandWithModification(initialCommands, infinityLoopIndexes);
                iterations = 0;
                accumulation = 0;
                nextIndexToBeRun = 0;
            }
            if (nextIndexToBeRun == lines.size()) {
                break;
            }
        }

        return accumulation;
    }

    private static int getAccumulation(int currentAccumulation,
                                       Pair<String, Integer> command) {

        if (command.getKey().equals("acc")) {
            currentAccumulation += command.getValue();
        }
        return currentAccumulation;
    }

    private static int getNextIndexToBeRun(int nextIndexToBeRun,
                                           String command,
                                           int number) {
        if (command.equals("jmp")) {
            nextIndexToBeRun = nextIndexToBeRun + number;
        } else {
            nextIndexToBeRun++;
        }
        return nextIndexToBeRun;
    }

    private static List<String> getCommandWithModification(List<String> initialList,
                                                           List<Integer> tried) {
        List<String> lines = new ArrayList<>(initialList);
        for (int i = 0; i < lines.size() - 1; i++) {
            Pair<String, Integer> command = getCommand(lines.get(i));
            if (isNotAlreadyTriedJumpCommand(tried, i, command)) {
                String element = lines.get(i).replace("jmp", "nop");
                lines.set(i, element);
                tried.add(i);
                break;
            }
        }
        return lines;
    }

    private static boolean isNotAlreadyTriedJumpCommand(List<Integer> tried,
                                                        int indexToModify,
                                                        Pair<String, Integer> command) {
        return command.getKey().equals("jmp")
                && !tried.contains(indexToModify);
    }

    private static Pair<String, Integer> getCommand(String line) {
        String[] split = line.split(" ");
        String command = split[0];
        int number = Integer.parseInt(split[1]);
        return new Pair<>(command, number);

    }

}
