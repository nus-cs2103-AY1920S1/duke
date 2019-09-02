package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test1() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Event event = new Event("CS2103T project", date);
        assertEquals("CS2103T project", event.description);
    }

    @Test
    public void test2() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Event event = new Event("CS2103T project", date);
        String expected = "[E][\u2718] CS2103T project (at: 28 August 2019 11:59 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void test3() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Event Event = new Event("CS2103T project", date);
        String dateString = date.toString();
        assertEquals(dateString, Event.at.toString());
    }
}
