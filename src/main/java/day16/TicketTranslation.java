package day16;


import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketTranslation {

    public static long getSum(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        HashMap<String, List<Pair<Integer, Integer>>> ranges = getFieldRanges(lines);
        List<String> nearbyTicket = getNearbyTickets(lines);
        List<List<Integer>> nearbyTickets = parseTickets(nearbyTicket);
        List<Pair<Integer, Integer>> allranges = getAllRanges(ranges);
        int sum = 0;
        for (List<Integer> tcket : nearbyTickets) {
            sum += isValueValue(tcket, allranges)
                    .stream()
                    .reduce(0, (a, b) -> a + b);
        }
        return sum;
    }

    private static List<Integer> isValueValue(List<Integer> integers, List<Pair<Integer, Integer>> ranges) {
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
        return invalidValues;
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
            for(String s : parsedTicket) {
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
            if (emptyLineCount > 1 && !line.contains("nearby tickets:")){
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
