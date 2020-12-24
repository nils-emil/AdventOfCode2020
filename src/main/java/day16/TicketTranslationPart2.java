package day16;


import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TicketTranslationPart2 {

    public static long getMultiplicationResult(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        HashMap<String, List<Pair<Integer, Integer>>> ranges = getFieldRanges(lines);
        List<String> fields = getFields(lines);
        String myTicket = getMyTicket(lines);
        String[] split = myTicket.split(",");
        List<String> nearbyTicket = getNearbyTickets(lines);
        List<Pair<Integer, Integer>> allranges = getAllRanges(ranges);
        List<List<Integer>> validTickets = parseTickets(nearbyTicket)
                .stream()
                .filter(e -> isValueValue(e, allranges))
                .collect(Collectors.toList());
        List<Integer> resultIndexes = new ArrayList<>();
        List<Pair<Integer, List<Integer>>> possibleIndexes = new ArrayList<>();
        int position = 0;
        for (String key : fields) {
            List<Integer> indexes = findIndexWhereAllValuesMatch(ranges.get(key), validTickets);
            Pair<Integer, List<Integer>> positionWithPossibleIndexes = new Pair<>(position, indexes);
            possibleIndexes.add(positionWithPossibleIndexes);
            position++;
        }
        possibleIndexes.sort(Comparator.comparing(integerListPair -> integerListPair.getValue().size()));
        int[] result = new int[split.length];
        for (Pair<Integer, List<Integer>> indexInner : possibleIndexes) {
            for (Integer integer : indexInner.getValue()) {
                if (!resultIndexes.contains(integer)) {
                    resultIndexes.add(integer);
                    result[indexInner.getKey()] = integer;
                    break;
                }
            }
        }
        long sum = Long.parseLong(split[result[0]]);
        for (int j = 1; j < 6; j++) {
            sum = sum * Long.parseLong(split[result[j]]);
        }
        return sum;
    }

    private static List<String> getFields(List<String> lines) {

        List<String> files = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }
            String[] split = line.split(": ");
            String fieldName = split[0];
            files.add(fieldName);
        }
        return files;
    }

    private static List<Integer> findIndexWhereAllValuesMatch(List<Pair<Integer, Integer>> ranges, List<List<Integer>> validTickets) {
        List<Integer> matchingIndexes = new ArrayList<>();
        for (int column = 0; column < validTickets.get(0).size(); column++) {
            int matches = 0;
            for (List<Integer> ticket : validTickets) {
                if (isValidRange(ticket.get(column), ranges)) {
                    matches++;
                }
            }
            if (matches == validTickets.size()) {
                matchingIndexes.add(column);
            }
        }
        return matchingIndexes;
    }

    private static boolean isValidRange(Integer integer, List<Pair<Integer, Integer>> ranges) {
        boolean match = false;
        for (Pair<Integer, Integer> rule : ranges) {
            if (matchesRange(rule, integer)) {
                match = true;
            }
        }
        return match;
    }

    private static boolean isValueValue(List<Integer> integers, List<Pair<Integer, Integer>> ranges) {
        List<Integer> invalidValues = new ArrayList<>();
        for (Integer number : integers) {
            boolean match = false;
            for (Pair<Integer, Integer> rule : ranges) {
                if (matchesRange(rule, number)) {
                    match = true;
                }
            }
            if (!match) {
                invalidValues.add(number);
            }
        }
        return invalidValues.isEmpty();
    }

    private static List<Pair<Integer, Integer>> getAllRanges(HashMap<String, List<Pair<Integer, Integer>>> ranges) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (List<Pair<Integer, Integer>> fieldRange : ranges.values()) {
            result.addAll(fieldRange);
        }
        return result;
    }

    private static boolean matchesRange(Pair<Integer, Integer> range, Integer ticketValue) {
        return ticketValue >= range.getKey() && ticketValue <= range.getValue();
    }


    private static List<List<Integer>> parseTickets(List<String> nearbyTicket) {
        List<List<Integer>> tickets = new ArrayList<>();
        for (String ticket : nearbyTicket) {
            String[] parsedTicket = ticket.split(",");
            List<Integer> parsedTicketNumbers = new ArrayList<>();
            for (String s : parsedTicket) {
                parsedTicketNumbers.add(Integer.parseInt(s));
            }
            tickets.add(parsedTicketNumbers);
        }
        return tickets;
    }

    private static List<String> getNearbyTickets(List<String> lines) {
        int emptyLineCount = 0;
        List<String> nearbyTickets = new ArrayList<>();
        for (String line : lines) {
            if (emptyLineCount > 1 && !line.contains("nearby tickets:")) {
                nearbyTickets.add(line);
            }
            if (line.isEmpty()) {
                emptyLineCount++;
            }
        }
        return nearbyTickets;
    }

    private static String getMyTicket(List<String> lines) {
        boolean nextLineIsMyTicket = false;
        for (String line : lines) {
            if (nextLineIsMyTicket) {
                return line;
            }
            if (line.contains("your ticket")) {
                nextLineIsMyTicket = true;
            }
        }
        return null;
    }

    private static HashMap<String, List<Pair<Integer, Integer>>> getFieldRanges(List<String> lines) {
        HashMap<String, List<Pair<Integer, Integer>>> fieldRanges = new HashMap<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }
            String[] split = line.split(": ");
            String fieldName = split[0];
            String[] ranges = split[1].split(" or ");
            List<Pair<Integer, Integer>> rangesResult = new ArrayList<>();
            for (String range : ranges) {
                String[] rangeFromTo = range.split("-");
                Pair<Integer, Integer> integerIntegerPair = new Pair<>(Integer.parseInt(rangeFromTo[0]), Integer.parseInt(rangeFromTo[1]));
                rangesResult.add(integerIntegerPair);
            }
            fieldRanges.put(fieldName, rangesResult);

        }
        return fieldRanges;
    }


}
