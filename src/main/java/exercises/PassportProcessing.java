package exercises;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class PassportProcessing {

    private static final String INCHES_UNIT = "in";
    private static final String CM_UNIT = "cm";
    private static final int REQUIRED_AMOUNT_OF_VALID_PARAMETERS = 7;
    private static final List<String> ALL_REQUIRED_PASSPORT_FIELDS =
            Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
    private static final List<String> VALID_EYE_COLORS =
            Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    public static int getNumberOfValidPassports(String inputfile) throws FileNotFoundException {
        List<String> input = FileUtils.getCsvLinesAsUniqueNames(inputfile);
        List<HashMap<String, String>> passports = parseCsvLinesToPassports(input);
        return getNumberOfValidPassports(passports);
    }

    private static int getNumberOfValidPassports(List<HashMap<String, String>> passports) {
        int validPassportCount = 0;
        for (HashMap<String, String> passport : passports) {
            if (isValidPassport(passport)) {
                validPassportCount++;
            }
        }
        return validPassportCount;
    }

    private static List<HashMap<String, String>> parseCsvLinesToPassports(List<String> input) {
        List<HashMap<String, String>> passports = new ArrayList<>();
        HashMap<String, String> tempPassport = new HashMap<>();
        for (String string : input) {
            if (string.isEmpty()) {
                passports.add(tempPassport);
                tempPassport = new HashMap<>();
            } else {
                String[] elements = string.split(" ");
                for (String element : elements) {
                    String[] split = element.split(":");
                    tempPassport.put(split[0], split[1]);
                }
            }
        }
        return passports;
    }

    private static boolean isValidPassport(HashMap<String, String> passwordValues) {
        int counter = 0;
        for (String required : ALL_REQUIRED_PASSPORT_FIELDS) {
            boolean isValid = isValid(required, passwordValues.get(required));
            if (passwordValues.containsKey(required) && isValid) {
                counter++;
                if (counter == REQUIRED_AMOUNT_OF_VALID_PARAMETERS) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> getRequiredFields() {
        return ALL_REQUIRED_PASSPORT_FIELDS;
    }

    private static boolean isValid(String field, String value) {
        if (value == null) {
            return false;
        }
        switch (field) {
            case "byr":
                return isDigidBetweenRange(value, 2002, 1920);
            case "iyr":
                return isDigidBetweenRange(value, 2020, 2010);
            case "eyr":
                return isDigidBetweenRange(value, 2030, 2020);
            case "hgt":
                return isValidHeight(value);
            case "hcl":
                return Pattern.compile("^#([a-z-0-9]{6})$").matcher(value).find();
            case "ecl":
                return VALID_EYE_COLORS.contains(value);
            case "pid":
                return value.chars().allMatch(Character::isDigit) && value.length() == 9;
            default:
                return "cid".equals(field);
        }
    }

    private static boolean isValidHeight(String value) {
        if (value.contains(CM_UNIT)) {
            return isValidHeight(value, CM_UNIT);
        }
        if (value.contains(INCHES_UNIT)) {
            return isValidHeight(value, INCHES_UNIT);
        }
        return false;
    }

    private static boolean isValidHeight(String value, String unit) {
        String heightWithoutUnit = value.replace(unit, "");
        if (CM_UNIT.equals(unit)) {
            return isDigidBetweenRange(heightWithoutUnit, 193, 150);
        }
        if (INCHES_UNIT.equals(unit)) {
            return isDigidBetweenRange(heightWithoutUnit, 76, 59);
        }
        return false;
    }

    private static boolean isDigidBetweenRange(String value, int i, int i2) {
        boolean allDigits = value.chars().allMatch(Character::isDigit);
        if (!allDigits) {
            return false;
        }
        return Integer.parseInt(value) <= i && Integer.parseInt(value) >= i2;
    }

}
