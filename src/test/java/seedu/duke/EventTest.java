package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion1() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        assertEquals("[E][✗] project meeting (at: 02/12/2019 15.21 PM)",
                new Event("project meeting", date).toString());
    }

    @Test
    public void testStringConversion2() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        Event eventTask = new Event("project meeting", date);
        eventTask.markAsDone();
        assertEquals("[E][✓] project meeting (at: 02/12/2019 15.21 PM)", eventTask.toString());
    }

    @Test
    public void testWriteToFile1() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("2/12/2019 1521");
        Event eventTask = new Event("project meeting", date);
        eventTask.markAsDone();
        assertEquals("E | 1 | project meeting | 02/12/2019 1521", eventTask.writeToFile());
    }

}
