package nl.oose;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private StringCalculator sut;

    @Before
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
        thrown.expectMessage("Negatieve waarde niet toegestaan: -1");
        sut.add("-1,2");
    }

    @Test
    public void testThatExceptionIsThrownForMultipleNegativeValues() {
        thrown.expectMessage("Negatieve waarde niet toegestaan: -1, -3");
        sut.add("-1,-3");
    }
}