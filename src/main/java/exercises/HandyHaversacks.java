package exercises;

import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandyHaversacks {

    private static final String SHINY_GOLD = "shiny gold";
    private static final int SELF_COUNT = 1;

    public static int howManyColorsCanHoldGoldenBag(String csv) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(csv);
        Map<String, List<String>> possibilities = getBagContentPossibilities(input);
        int count = 0;
        for (String color : possibilities.keySet()) {
            count += HandyHaversacks.canFitGoldenBag(color, possibilities) ? 1 : 0;
        }
        return count;
    }

    public static int howManyIndividualBagsInsideGoldenBag(String csv) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLines(csv);
        Map<String, List<String>> possibilities = getBagContentPossibilities(input);
        int howManyBagsIncludingSelf = howManyBagsInsideWithSelfIncluded("0 " + SHINY_GOLD, possibilities);
        return howManyBagsIncludingSelf - SELF_COUNT;
    }

    private static int howManyBagsInsideWithSelfIncluded(String color, Map<String, List<String>> possibilities) {
        String inputColor = color.substring(2);
        List<String> possibleOuterBagColors = possibilities.get(inputColor);
        if (possibleOuterBagColors.isEmpty()) {
            return SELF_COUNT;
        }
        int count = 0;
        for (String possiblecolor : possibleOuterBagColors) {
            int howManyBags = Integer.parseInt(possiblecolor.substring(0, 1));
            int howManyBagsInside = howManyBagsInsideWithSelfIncluded(possiblecolor, possibilities);
            count += howManyBags * howManyBagsInside;
        }
        return SELF_COUNT + count;
    }

    private static boolean canFitGoldenBag(String color, Map<String, List<String>> possibilities) {
        List<String> outerBagColors = possibilities.get(color);
        for (String possibleColor : outerBagColors) {
            if (possibleColor.contains(SHINY_GOLD) || canFitGoldenBag(possibleColor.substring(2), possibilities)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, List<String>> getBagContentPossibilities(List<String> lines) {
        Map<String, List<String>> colorsMap = new HashMap<>();
        for (String line : lines) {
            Pair<String, List<String>> pair = colorsToColors(line);
            colorsMap.put(pair.getKey(), pair.getValue());
        }
        return colorsMap;
    }

    private static Pair<String, List<String>> colorsToColors(String line) {
        List<String> colors = new ArrayList<>();
        String[] split = line.split(" contain ");
        String container = split[0].replace(" bags", "");
        String[] possibleColors = split[1].split(", ");
        for (String possibleColor : possibleColors) {
            if (!possibleColor.contains("no other")) {
                String parsedColor = colorWithNoiseRemoved(possibleColor);
                colors.add(parsedColor);
            }
        }
        return new Pair<>(container, colors);
    }

    private static String colorWithNoiseRemoved(String possibleColor) {
        String parsedColor = possibleColor.replace(".", "");
        parsedColor = parsedColor.replace(" bags", "");
        parsedColor = parsedColor.replace(" bag", "");
        return parsedColor;
    }

}
