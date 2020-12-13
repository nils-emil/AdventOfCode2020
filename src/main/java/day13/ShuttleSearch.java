package day13;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShuttleSearch {

    public static int getEarliestBusToTake(String inputfile) throws FileNotFoundException {
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


    public static long getEarliestSbsequentTimeStamp(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        String[] busses = lines.get(1).split(",");
        List<Long> busIds = new ArrayList<>();
        HashMap<Long, Long> busOffsets = new HashMap<>();
        long largestBusId = 0;
        long largestBusIdIndex = 0;
        long index = 0;
        for (String bus : busses) {
            if (bus.chars().allMatch(Character::isDigit)) {
                long e = Long.parseLong(bus);
                busIds.add(e);
                busOffsets.put(e, index);
                if (e > largestBusId) {
                    largestBusId = e;
                    largestBusIdIndex = index;
                }
            }
            index ++;
        }
        long nextTimestampToCheck = 0L;
        while (true) {
            nextTimestampToCheck += largestBusId;
            long numberOfBussesThatMatched = 0;
            for (long busId : busIds) {
                long busOffset = busOffsets.get(busId);
                long timeStampBusIdShouldMatch = nextTimestampToCheck - largestBusIdIndex + busOffset;
                if (timeStampBusIdShouldMatch % busId == 0) {
                    numberOfBussesThatMatched++;
                }
            }
            if (numberOfBussesThatMatched == busIds.size()) {
                return nextTimestampToCheck - largestBusIdIndex;
            }
        }
    }
}
