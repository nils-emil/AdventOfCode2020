package day13;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShuttleSearch {
    public static int getManhattanDistance(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        int timestamp = Integer.parseInt(lines.get(0));
        String[] busses = lines.get(1).split(",");
        List<Integer> busIds = new ArrayList<>();
        for (String bus : busses) {
            if (bus.chars().allMatch(Character::isDigit)) {
                busIds.add(Integer.parseInt(bus));
            }
        }
        int currentEarliest = Integer.MAX_VALUE;
        int currentBusId = 0;
        for (int busId : busIds) {
            int i = timestamp % busId;
            int earliest = (timestamp - i) + busId;
            if (earliest < currentEarliest) {
                currentEarliest = earliest;
                currentBusId = busId;
            }
        }
        return currentBusId * (currentEarliest - timestamp);
    }
}
