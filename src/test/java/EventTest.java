import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void testStringConversion() throws Exception{
        assertEquals("[E][\u2718] test event (at: 1st of January 2019, 06:00PM)", (new Event("test event","1/1/2019 1800")).toString());
    }

    @Test
    public void dateToStringConversion_validDates_success() throws Exception {
        assertEquals("1st of January 2019, 06:00PM", (new Event("test event 1","1/1/2019 1800")).dateToString());
        assertEquals("13th of February 2019, 09:00AM", (new Event("test event 2","13/2/2019 0900")).dateToString());
        assertEquals("3rd of August 2019, 05:30PM", (new Event("test event 3","3/8/2019 1730")).dateToString());
    }

    @Test
    public void dateToStringConversion_inValidDates_exceptionThrown() throws Exception {
        try {
            assertEquals(0, (new Event("test event 4","monday")).dateToString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The format of event timing is invalid.", e.getMessage());
        }
    }

}
