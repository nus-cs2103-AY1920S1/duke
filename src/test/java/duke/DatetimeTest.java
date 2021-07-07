package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatetimeTest {
    @Test
    public void testDatetime() {
        assertEquals("31st of August 2019", new Datetime("31/08/2019 0000").getDate());
        assertEquals("2nd of September 2020", new Datetime("02/09/2020 0000").getDate());
        assertEquals("27th of October 1997", new Datetime("27/10/1997 0000").getDate());
    }

    @Test
    public void testTime() {
        assertEquals("12:00am", new Datetime("01/01/2000 0000").getTime());
        assertEquals("11:59pm", new Datetime("01/01/2000 2359").getTime());
        assertEquals("12:35pm", new Datetime("01/01/2000 1235").getTime());
    }
}
