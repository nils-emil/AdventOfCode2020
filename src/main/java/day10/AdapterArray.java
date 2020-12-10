package day10;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdapterArray {

    public static int getJoltDifferenceMultiplication(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Integer> inputNumber = lines.stream().map(Integer::new).collect(Collectors.toList());
        List<Integer> usedAdapters = Collections.emptyList();
        Collections.sort(inputNumber);
        int count1 = 0;
        int count3 = 0;
        for (int i : inputNumber) {
            int diff = AdapterArray.getJoltDifferences(i, usedAdapters, inputNumber);
            if (diff - i == 1) {
                count1 += 1;
            }
            if (diff - i == 3) {
                count3 += 1;
            }
        }
        return (count1 + 1) * (count3 + 1);
    }

    private static int getJoltDifferences(int i, List<Integer> usedAdapters, List<Integer> available) {
        if (!usedAdapters.contains(i + 1) && available.contains(i + 1)) {
            return i + 1;
        }
        if (!usedAdapters.contains(i + 2) && available.contains(i + 2)) {
            return i + 2;
        }
        if (!usedAdapters.contains(i + 3) && available.contains(i + 3)) {
            return i + 3;
        }
        return 0;
    }

    public static long countNumberOfWays(String inputStr) throws FileNotFoundException {
        ArrayList<Integer> input = new ArrayList<>();
        input.add(0);
        List<String> lines = FileUtils.getCsvLines(inputStr);
        input.addAll(lines.stream().map(Integer::new).collect(Collectors.toList()));
        Collections.sort(input);
        input.add(input.get(input.size() - 1) + 3);
        int len = input.size();
        long[] counter = new long[len];
        Arrays.fill(counter, -1);
        return countNumberOfWays(input, 0, counter);
    }

    public static long countNumberOfWays(ArrayList<Integer> adapters,
                                         int index,
                                         long[] counter) {
        if (index == adapters.size() - 1) {
            return 1;
        }
        if (counter[index] != -1) {
            return counter[index];
        }
        int maxIndex = index + 3;
        if (maxIndex >= adapters.size()) {
            maxIndex = adapters.size() - 1;
        }
        long count = IntStream.rangeClosed(index + 1, maxIndex)
                .filter(i -> adapters.get(index) + 3 >= adapters.get(i))
                .mapToLong(i -> countNumberOfWays(adapters, i, counter))
                .sum();
        counter[index] = count;
        return count;
    }


}
