package day02;

import java.util.HashMap;

public class PasswordValidator {

    public static final String SPACE = " ";
    public static final String RANGE_SEPARATOR_SYMBOL = "-";
    public static final int MIN_BLOCK_INDEX = 0;
    public static final int MAX_BLOCK_INDEX = 1;
    public static final int CHARACTER_BLOCK_INDEX = 1;
    public static final int PASSWORD_BLOCK_INDEX = 2;

    public static boolean hasValidAmountOfRequiredCharacters(String input) {
        HashMap<String, String> data = getVariablesFromCsvLine(input);
        int min = Integer.parseInt(data.get("min"));
        int max = Integer.parseInt(data.get("max"));
        int count = 0;
        for (int i = 0; i < data.get("password").length(); i++) {
            if (data.get("password").charAt(i) == data.get("letter").charAt(0)) {
                count++;
            }
        }
        return count <= max && count >= min;
    }

    public static boolean hasExactlyOneCharacterAtSpecifiedPositions(String input) {
        HashMap<String, String> data = getVariablesFromCsvLine(input);
        int min = Integer.parseInt(data.get("min"));
        int max = Integer.parseInt(data.get("max"));
        int count = 0;
        if (data.get("password").charAt(min - 1) == data.get("letter").charAt(0)) {
            count++;
        }
        if (data.get("password").charAt(max - 1) == data.get("letter").charAt(0)) {
            count++;
        }
        return count == 1;
    }

    private static HashMap<String, String> getVariablesFromCsvLine(String input) {
        String[] blocks = input.split(SPACE);
        String lengthBlock = blocks[0];
        String[] ranges = lengthBlock.split(RANGE_SEPARATOR_SYMBOL);
        String letter = blocks[CHARACTER_BLOCK_INDEX];
        String password = blocks[PASSWORD_BLOCK_INDEX];
        HashMap<String, String> data = new HashMap<>();
        data.put("password", password);
        data.put("letter", letter);
        data.put("min", ranges[MIN_BLOCK_INDEX]);
        data.put("max", ranges[MAX_BLOCK_INDEX]);
        return data;
    }

}
