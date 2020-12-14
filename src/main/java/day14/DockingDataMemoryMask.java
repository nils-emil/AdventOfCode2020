package day14;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DockingDataMemoryMask {

    public static final String BINARY_ZERO_LEN_32 = "000000000000000000000000000000000000";

    public static long getMemorySum(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        HashMap<String, String> memory = new HashMap<>();
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        for (String line : lines) {
            if (line.startsWith("mask = ")) {
                mask = line.replace("mask = ", "");
            } else {
                String[] memoryValue = line.split(" = ");
                String memoryAddress = memoryValue[0].replace("mem[", "").replace("]", "");
                String binaryMemoryAddress = Integer.toBinaryString(Integer.parseInt(memoryAddress));
                String paddedMemoryAddress = BINARY_ZERO_LEN_32.substring(binaryMemoryAddress.length()) + binaryMemoryAddress;
                String memoryAddressesMaskApplied = applyMask(paddedMemoryAddress, mask);
                Set<String> memoryAddresses = getPossibleVariations(memoryAddressesMaskApplied);
                String binaryNumber = Integer.toBinaryString(Integer.parseInt(memoryValue[1]));
                String padded = BINARY_ZERO_LEN_32.substring(binaryNumber.length()) + binaryNumber;
                for (String address : memoryAddresses) {
                    memory.put(address, padded);
                }
            }
        }
        return memory.keySet().stream().mapToLong(key -> Long.parseLong(memory.get(key), 2)).sum();
    }

    private static String applyMask(String padded, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= mask.length() - 1; i++) {
            if (mask.charAt(i) == 'X') {
                result.append('X');
            } else if (mask.charAt(i) == '1') {
                result.append(1);
            } else {
                result.append(padded.charAt(i));
            }
        }
        return result.toString();
    }

    private static Set<String> getPossibleVariations(String binaryString) {
        Set<String> result = new HashSet<>();
        result.add(binaryString);
        while (true) {
            Set<String> stringToAddToResult = new HashSet<>();
            Set<String> stringToRemove = new HashSet<>();
            for (String stringToProcess : result) {
                if (stringToProcess.contains("X")) {
                    stringToRemove.add(stringToProcess);
                    stringToAddToResult.add(stringToProcess.replaceFirst("X", "1"));
                    stringToAddToResult.add(stringToProcess.replaceFirst("X", "0"));
                    break;
                }
            }
            result.addAll(stringToAddToResult);
            result.removeAll(stringToRemove);
            int numberOfResultsWithOutX = 0;
            for (String string : result) {
                if (!string.contains("X")) {
                    numberOfResultsWithOutX++;
                }
            }
            if (numberOfResultsWithOutX == result.size()) {
                break;
            }
        }
        return result;
    }

}
