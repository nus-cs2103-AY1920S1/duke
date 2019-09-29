package duke.formats;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    String expectedToString = " 18th of September 1997, 8.00am";

    @Test
    public void testToString() {
        DateTime dateTime = new DateTime("18/09/1997 0800");
        assertEquals(expectedToString, dateTime.toString());
    }
}
