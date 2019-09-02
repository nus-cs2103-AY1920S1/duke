package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void test1() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        String expected = "[E][\u2718] CS2103T project (at: 03 March 2019 01:00 AM)";
        ArrayList<Task> list = new ArrayList<>();
        list.add(event);
        TaskList tl = new TaskList(list);
        assertEquals(expected, tl.list.get(0).toString());
    }

    @Test
    public void test2() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        ArrayList<Task> list = new ArrayList<>();
        list.add(event);
        TaskList tl = new TaskList(list);
        Command c = new DeleteCommand(0);
        c.execute(tl, new Ui(), new Storage(""));
        assertEquals(0, tl.list.size());
    }

    @Test
    public void test3() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        ArrayList<Task> list = new ArrayList<>();
        list.add(event);
        list.add(new ToDo("one"));
        list.add(new ToDo("two"));
        list.add(new ToDo("three"));
        TaskList tl = new TaskList(list);
        Command c = new DeleteCommand(0);
        c.execute(tl, new Ui(), new Storage(""));
        assertEquals(3, tl.list.size());
    }
}
