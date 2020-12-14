package day14;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class DockingData {

    public static final String BINARY_ZERO_LEN_32 = "000000000000000000000000000000000000";

    public static long getEarliestBusToTake(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        HashMap<String, String> memory = new HashMap<>();
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        for (String line : lines) {
            if (line.startsWith("mask = ")) {
                mask = line.replace("mask = ", "");
            } else {
                String[] memoryValue = line.split(" = ");
                String memoryAddress = memoryValue[0].replace("mem[", "").replace("]", "");
                String binaryNumber = Integer.toBinaryString(Integer.parseInt(memoryValue[1]));
                String padded = BINARY_ZERO_LEN_32.substring(binaryNumber.length()) + binaryNumber;
                String bindryWithMaskApplies = applyMask(padded, mask);
                memory.put(memoryAddress, bindryWithMaskApplies);
            }
        }
        return memory.keySet().stream().mapToLong(key -> Long.parseLong(memory.get(key), 2)).sum();
    }

    private static String applyMask(String padded, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= mask.length() - 1; i++) {
            if (mask.charAt(i) == 'X') {
                result.append(padded.charAt(i));
            } else {
                result.append(mask.charAt(i));
            }
        }
        return result.toString();
    }

}
