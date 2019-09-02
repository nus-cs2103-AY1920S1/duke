package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test1() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Deadline deadline = new Deadline("CS2103T project", date);
        assertEquals("CS2103T project", deadline.description);
    }

    @Test
    public void test2() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Deadline deadline = new Deadline("CS2103T project", date);
        String expected = "[D][\u2718] CS2103T project (by: 28 August 2019 11:59 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void test3() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Deadline deadline = new Deadline("CS2103T project", date);
        String dateString = date.toString();
        assertEquals(dateString, deadline.by.toString());
    }
}
