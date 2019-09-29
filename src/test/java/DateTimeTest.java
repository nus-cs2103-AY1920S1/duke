import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kappa.elements.DateTime;

import kappa.exception.KappaException;

/**
 * Test class for DateTime.
 */
class DateTimeTest {

    /**
     * Tests if the date has been converted properly as intended.
     *
     * @throws KappaException For invalid dates.
     */
    @Test
    void testDateTime() throws KappaException {
        DateTime date = new DateTime("1/12/2000","0033");
        assertEquals("1st of December 2000, 12.33am", date.convertToString());
    }

}