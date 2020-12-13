package day13;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    private static long inv(long a, long m) {
        long m0 = m, t, q;
        long x0 = 0, x1 = 1;
        if (m == 1) {
            return 0;
        }
        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0) {
            x1 += m0;
        }
        return x1;
    }


    // Chinise remainder theorem
    // copy paste from https://www.geeksforgeeks.org/chinese-remainder-theorem-set-2-implementation/
    private static long findMinX(Long[] num, Long[] rem, int k) {
        long prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }
        long result = 0;
        for (int i = 0; i < k; i++) {
            long pp = prod / num[i];
            result += rem[i] * inv(pp, num[i]) * pp;
        }
        return result % prod;
    }

    // Chinise remainder theorem
    // copy paste from https://www.geeksforgeeks.org/chinese-remainder-theorem-set-2-implementation/
    public static long getEarliestSbsequentTimeStamp(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        String[] busses = lines.get(1).split(",");
        List<Long> busIds = new ArrayList<>();
        List<Long> busRequiredReminders = new ArrayList<>();
        long index = 0;
        for (String bus : busses) {
            if (bus.chars().allMatch(Character::isDigit)) {
                long e = Long.parseLong(bus);
                busIds.add(e);
                busRequiredReminders.add(e - (index % e));
            }
            index++;
        }
        Long[] num = new Long[busIds.size()];
        busIds.toArray(num);
        Long[] rem = new Long[busRequiredReminders.size()];
        busRequiredReminders.toArray(rem);
        int k = num.length;
        return findMinX(num, rem, k);
    }
}
