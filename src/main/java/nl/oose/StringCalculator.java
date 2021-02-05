package nl.oose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String DELIMITER_ESCAPE_SEQUENCE = "//";
    private static final String STANDARD_DELIMITER = ",";

    public int add(String stringContainingNumbers) {
        DelimitedString delimitedString = toDelimitedString(stringContainingNumbers);
        List<Integer> values = parseNumbers(delimitedString);
        checkForNegativeValues(values);
        return sumValues(values);
    }

    private int sumValues(List<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    private void checkForNegativeValues(List<Integer> values) {
        List<Integer> negativeValues = new ArrayList<>();
        for (Integer value : values) {
            if (value < 0) {
                negativeValues.add(value);
            }
        }
        if (listContainsValues(negativeValues)) {
            throw new RuntimeException("Negatieve waarde niet toegestaan: "
                    + toListString(negativeValues));
        }
    }

    private boolean listContainsValues(List<Integer> negativeValues) {
        return !negativeValues.isEmpty();
    }


    private String toListString(List<Integer> negativeValues) {
        return Arrays.toString(negativeValues.toArray())
                .replace("[", "")
                .replace("]", "");
    }

    private List<Integer> parseNumbers(DelimitedString delimitedString) {
        return Arrays.stream(delimitedString.getValue()
                .split(delimitedString.getDelimiter()))
                .filter(value -> !value.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private DelimitedString toDelimitedString(String stringContainingNumbers) {
        String delimiter = STANDARD_DELIMITER;
        if (delimiterIsSpecified(stringContainingNumbers)) {
            delimiter = stringContainingNumbers.substring(2, 3);
            stringContainingNumbers = stringContainingNumbers.substring(3);
        }
        delimiter = "\n|" + delimiter;
        return new DelimitedString(stringContainingNumbers, delimiter);
    }

    private boolean delimiterIsSpecified(String stringContainingNumbers) {
        return stringContainingNumbers.startsWith(DELIMITER_ESCAPE_SEQUENCE);
    }

}
