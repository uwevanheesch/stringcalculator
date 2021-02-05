package nl.oose;

public class DelimitedString {

    private String value;
    private String delimiter;

    public DelimitedString(String value, String delimiter) {
        this.value = value;
        this.delimiter = delimiter;
    }

    public String getValue() {
        return value;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
