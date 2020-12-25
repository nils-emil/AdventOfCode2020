package day18;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationOrder {

    public static final Pattern NUMERIC_OPERATIONS = Pattern.compile("(^[0-9]+[^0-9][0-9])([^0-9])(.*)");
    public static final Pattern STARTS_WITH_PARENTHASIS = Pattern.compile("\\((.*?)\\)(.)(.*)");
    public static final Pattern NESTED_PARENTHASIS = Pattern.compile("(^[0-9]*)([^0-9])(\\(.*?\\))(^\\).*|$)");
    public static final Pattern SEQUENCED = Pattern.compile("(^[0-9]*)([^0-9])(\\(.*?\\))(^\\).*|$)");
    public static final Pattern SIMPLE = Pattern.compile("(^[0-9]*[^0-9]+[0-9]*$)");

    public static long getSum(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);

        return 0;
    }

    public static long getCalulcatonResult(String line) throws FileNotFoundException {
        String parsableString = line.replace(" ", "");
        boolean isNumeric = parsableString.chars().allMatch(Character::isDigit);
        Matcher nestedParenthesis = NESTED_PARENTHASIS.matcher(parsableString);
        Matcher sequencedParenthasis = SEQUENCED.matcher(parsableString);
        Matcher numericOperation = NUMERIC_OPERATIONS.matcher(parsableString);
        Matcher simple = SIMPLE.matcher(parsableString);
        boolean sequencedParenthasisResult = sequencedParenthasis.find();
        boolean numericOperationResult = numericOperation.find();
        boolean simpleResult = simple.find();
        if (parsableString.startsWith("(")) {
            return getResultWhenStartsWithParenthasis(parsableString);
        } else if (isNumeric) {
            return Long.parseLong(parsableString);
        } else if (simpleResult) {
            return getOperationResult(parsableString);
        } else if (numericOperationResult) {
            String parenthesisContent = numericOperation.group(1);
            String operator = numericOperation.group(2);
            String rest = numericOperation.group(3);
            return getCalulcatonResult(getOperationResult(parenthesisContent).toString() + operator + rest);
        } else if (sequencedParenthasisResult) {
            String parenthesisContent = nestedParenthesis.group(1);
            String operator = nestedParenthesis.group(2);
            String rest = nestedParenthesis.group(3);
            String parenthesis = getFirstParenhesis(rest);
            Long operationResult = getOperationResult(parenthesisContent + operator + getCalulcatonResult(parenthesis));
            String parenthsisRemoved = rest.replace("("+parenthesis+")", "");
            return getCalulcatonResult(operationResult + parenthsisRemoved);
        } else if (sequencedParenthasisResult) {
            return 0L;
        }
        return 0;
    }

    private static String getFirstParenhesis(String rest) {
        int operingParenthesisCount = 0;
        int closingParenthesisCount = 0;
        String result = "";
        for (String chraceter : rest.split("")) {
            if (chraceter.equals("(")) {
                operingParenthesisCount++;
            } else if (chraceter.equals(")") && operingParenthesisCount == closingParenthesisCount + 1) {
                return result;
            } else if (chraceter.equals(")")){
                closingParenthesisCount++;
            }
            if (!chraceter.equals("(") && !chraceter.equals(")")) {
                result += chraceter;
            }
        }
        return result;
    }

    private static Long getResultWhenStartsWithParenthasis(String parsableString) throws FileNotFoundException {
        Matcher m = STARTS_WITH_PARENTHASIS.matcher(parsableString);
        m.find();
        String parenthesisContent = m.group(1);
        String operator = m.group(2);
        String rest = m.group(3);
        if (operator.equals("+")) {
            return getCalulcatonResult(parenthesisContent) + getCalulcatonResult(rest);
        }
        if (operator.equals("*")) {
            return getCalulcatonResult(parenthesisContent) * getCalulcatonResult(rest);
        }
        if (operator.equals("-")) {
            return getCalulcatonResult(parenthesisContent) - getCalulcatonResult(rest);
        }
        if (operator.equals("/")) {
            return getCalulcatonResult(parenthesisContent) / getCalulcatonResult(rest);
        }
        return 0L;
    }


    public static Long getOperationResult(String parsableString) {
        Matcher m = Pattern.compile("([0-9]*)([^0-9]+)([0-9]*)").matcher(parsableString);
        m.find();
        String parenthesisContent = m.group(1);
        String operator = m.group(2);
        String rest = m.group(3);
        if (operator.equals("+")) {
            return Long.parseLong(parenthesisContent) + Long.parseLong(rest);
        }
        if (operator.equals("*")) {
            return Long.parseLong(parenthesisContent) * Long.parseLong(rest);
        }
        if (operator.equals("-")) {
            return Long.parseLong(parenthesisContent) - Long.parseLong(rest);
        }
        if (operator.equals("/")) {
            return Long.parseLong(parenthesisContent) / Long.parseLong(rest);
        }
        return 0L;
    }

}
