package day05;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding {

    public static int findMySeatId(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Integer> seatIds = lines.stream()
                .map(BinaryBoarding::getSeatId)
                .collect(Collectors.toList());

        int maxId = Collections.max(seatIds);

        for (int i = 1; i <= maxId; i++) {
            if (doesSeatIdBelongToMe(seatIds, i)) {
                return i;
            }
        }
        return -1;
    }

    private static int getSeatId(String e) {
        int rowId = parseCrypticBinaryStringToDecimalInt(e, 0, 7, "B", "F");
        int colId = parseCrypticBinaryStringToDecimalInt(e, 7, 10, "R", "L");
        return (rowId * 8) + colId;
    }

    private static int parseCrypticBinaryStringToDecimalInt(String e,
                                                            int startingIndex,
                                                            int endIndex,
                                                            String binaryTrue, String binaryFalse) {
        String rowSubString = e.substring(startingIndex, endIndex);
        rowSubString = rowSubString.replace(binaryTrue, "1");
        rowSubString = rowSubString.replace(binaryFalse, "0");
        return Integer.parseInt(rowSubString, 2);
    }

    private static boolean doesSeatIdBelongToMe(List<Integer> seatIds,
                                                int number) {
        return !seatIds.contains(number) &&
                seatIds.contains(number - 1) &&
                seatIds.contains(number + 1);
    }


}
