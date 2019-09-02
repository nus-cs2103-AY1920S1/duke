package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void test1() {
        Date date = new GregorianCalendar(2019, 7, 28, 23, 59).getTime();
        Event event = new Event("CS2103T project", date);
        event.done();
        String expected = "[E][\u2713] CS2103T project (at: 28 August 2019 11:59 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void test2() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        String expected = "[E][\u2718] CS2103T project (at: 03 March 2019 01:00 AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void test3() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        String expected = "[E][\u2713] CS2103T project (at: 03 March 2019 01:00 AM)";
        ArrayList<Task> list = new ArrayList<>();
        list.add(event);
        TaskList tl = new TaskList(list);
        Command c = new DoneCommand(0);
        c.execute(tl, new Ui(), new Storage(""));
        assertEquals(expected, tl.list.get(0).toString());
    }
}
