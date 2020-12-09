package exercises;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError {

    public static final int PREAMBLE = 25;

    public static long findEncryptionWeakness(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Long> inputNumber = lines.stream().map(Long::new).collect(Collectors.toList());
        int index = 0;
        for (long invalidNumber : inputNumber) {
            if (index > PREAMBLE) {
                boolean containsSumOfRequired = containsSumWithPreambleLimit(inputNumber, index, invalidNumber);
                if (!containsSumOfRequired) {
                    return getSumOfSublistMinMax(inputNumber.subList(0, index), invalidNumber);
                }
            }
            index++;
        }
        return -1;
    }

    private static boolean containsSumWithPreambleLimit(List<Long> inputNumber, int index, long invalidNumber) {
        int staringIndex = 0;
        if (index > PREAMBLE) {
            staringIndex = index - PREAMBLE;
        }
        List<Long> previousNumbersWithPreambleLimit = inputNumber.subList(staringIndex, index);
        return containsSum(previousNumbersWithPreambleLimit, invalidNumber);
    }

    private static long getSumOfSublistMinMax(List<Long> inputNumber, long invalidNumber) {
        List<Long> sums = findSublistThatSumsToRequired(inputNumber, invalidNumber);
        long max = sums.stream().max(Long::compareTo).get();
        long min = sums.stream().min(Long::compareTo).get();
        return max + min;
    }

    private static List<Long> findSublistThatSumsToRequired(List<Long> numbers, Long required) {
        List<Long> nums = new ArrayList<>();
        for (Long number : numbers) {
            if (listSumEquals(required, nums)) {
                return nums;
            }
            nums.add(number);
            nums = sublistBelowRequiredSum(nums, required);
        }
        return nums;
    }

    private static List<Long> sublistBelowRequiredSum(List<Long> nums, Long required) {
        List<Long> numbers = new ArrayList<>(nums);
        while (listSumIsBiggerThenRequired(required, numbers)) {
            numbers.remove(0);
        }
        return numbers;
    }

    private static boolean listSumIsBiggerThenRequired(Long required, List<Long> numbers) {
        return numbers
                .stream()
                .reduce(0L, Long::sum) > required;
    }

    private static boolean listSumEquals(Long required, List<Long> numbers) {
        return numbers
                .stream()
                .reduce(0L, Long::sum)
                .equals(required);
    }

    private static boolean containsSum(List<Long> sublist, Long num) {
        for (int i = 0; i < sublist.size(); i++) {
            for (int j = sublist.size() - 1; j > 0; j--) {
                if (sublist.get(i) + sublist.get(j) == num) {
                    return true;
                }
            }
        }
        return false;
    }

}
