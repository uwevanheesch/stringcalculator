package nl.oose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    private StringCalculator sut;

    @BeforeEach
    public void setUp() throws Exception {
        sut = new StringCalculator();
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, sut.add(""));
    }

    @Test
    public void testSingleNumberIsReturned() {
        assertEquals(1, sut.add("1"));
    }

    @Test
    public void testThatTwoNumbersAreAdded() {
        assertEquals(3, sut.add("1,2"));
    }

    @Test
    public void testThatThreeNumbersAreAdded() {
        assertEquals(4, sut.add("1,2,1"));
    }

    @Test
    public void testThatNewlineCanBeUsedAsAdditionalDelimiter() {
        assertEquals(7, sut.add("2\n3,2"));
    }

    @Test
    public void testThatDelimiterCanBeSpecified() {
        assertEquals(3, sut.add("//;\n1;2"));
    }

    @Test
    public void testThatExceptionIsThrownForNegativeValues() {
        Exception thrown = assertThrows(Exception.class, () -> {
            sut.add("-1,2");
        });
        assertEquals("Negatieve waarde niet toegestaan: -1", thrown.getMessage());
    }

    @Test
    public void testThatExceptionIsThrownForMultipleNegativeValues() {
        Exception thrown = assertThrows(Exception.class, () -> {
            sut.add("-1,-3");
        });
        assertEquals("Negatieve waarde niet toegestaan: -1, -3", thrown.getMessage());
    }


}