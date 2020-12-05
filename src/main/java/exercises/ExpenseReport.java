package exercises;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseReport {

    public static int getMultiplicationOfTwoNumbersThatSumTo2020(String s) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(s);
        List<Integer> collect = input.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for (int i = 0; i < collect.size() - 1; i++) {
            int num1 = collect.get(i);
            for (int j = collect.size() - 1; j > 0; j = j - 1) {
                int num2 = collect.get(j);
                if (num1 + num2 == 2020) {
                    return num1 * num2;
                }
            }
        }
        return -1;
    }

    public static int getMultiplicationOfThreeNumbersThatSumTo2020(String s) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(s);
        List<Integer> collect = input.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for (int i = 0; i < collect.size() - 1; i++) {
            int num1 = collect.get(i);
            for (int j = collect.size() - 1; j > 0; j = j - 1) {
                int num2 = collect.get(j);
                for (int k = 0; k < collect.size() - 1; k++) {
                    int num3 = collect.get(k);
                    if (num1 + num2  + num3 == 2020 && i != j && j != k && i != k) {
                        return num1 * num2 * num3;
                    }
                }
            }
        }
        return -1;
    }
}
