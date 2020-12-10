package day6;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class CustomsCustoms {

    public static int getSumOfCollectiveAnswers(String csv) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(csv);
        List<Set<String>> passports = parseCsvLinesToAnswers(input);
        int count = 0;
        for (Set<String> answers : passports) {
            count += answers.size();
        }
        return count;
    }

    private static List<Set<String>> parseCsvLinesToAnswers(List<String> input) {
        List<Set<String>> allAnswers = new ArrayList<>();
        Set<String> currentGroupsAnswer = new HashSet<>();
        boolean isFirstAnswerOfTheGroup = true;
        for (String string : input) {
            if (string.isEmpty()) {
                allAnswers.add(currentGroupsAnswer);
                isFirstAnswerOfTheGroup = true;
                currentGroupsAnswer = new HashSet<>();
            } else {
                String[] elements = string.split("");
                Set<String> answersInner = new HashSet<>(Arrays.asList(elements));
                if (isFirstAnswerOfTheGroup) {
                    currentGroupsAnswer = new HashSet<>(answersInner);
                    isFirstAnswerOfTheGroup = false;
                } else {
                    currentGroupsAnswer.retainAll(answersInner);
                }
            }
        }
        return allAnswers;
    }


}
