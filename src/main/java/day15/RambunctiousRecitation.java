package day15;


import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RambunctiousRecitation {


    public static long getNthNumber(List<Integer> integers) {
        Map<Integer, Pair<Integer, Integer>> latestAge = new HashMap<>();
        for (int num : integers) {
            latestAge.put(num, new Pair(integers.indexOf(num) + 1, 0));
        }
        int mostRecentSpokenNumber = integers.get(integers.size() - 1);
        for (int i = integers.size(); i < 30000000; i++) {
            Pair<Integer, Integer> age = latestAge.get(mostRecentSpokenNumber);
            if (age.getValue() == 0) {
                mostRecentSpokenNumber = 0;
                latestAge.put(mostRecentSpokenNumber, new Pair(i + 1, latestAge.get(mostRecentSpokenNumber).getKey()));
            } else {
                mostRecentSpokenNumber = age.getKey() - age.getValue();
                updateAge(latestAge, mostRecentSpokenNumber, i);
            }
        }
        return mostRecentSpokenNumber;
    }

    private static void updateAge(Map<Integer, Pair<Integer, Integer>> latestAge,
                                  int mostRecentSpokenNumber,
                                  int nowAge) {
        if (latestAge.containsKey(mostRecentSpokenNumber)) {
            Pair age = new Pair(nowAge + 1, latestAge.get(mostRecentSpokenNumber).getKey());
            latestAge.put(mostRecentSpokenNumber, age);
        } else {
            Pair age = new Pair(nowAge + 1, 0);
            latestAge.put(mostRecentSpokenNumber, age);
        }
    }


    public static void main(String[] args) {
        System.out.println(getNthNumber(Arrays.asList(1, 20, 8, 12, 0, 14))); //492 if 2020 and 63644 if 30000000
    }
}
