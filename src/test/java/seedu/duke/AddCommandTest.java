package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void test1() {
        Date date = new GregorianCalendar(2019, 2, 3, 1, 0).getTime();
        Event event = new Event("CS2103T project", date);
        ArrayList<Task> list = new ArrayList<>();
        TaskList tl = new TaskList();
        list.add(event);
        TaskList other = new TaskList(list);
        Command c = new AddCommand("event", "CS2103T project /at 3/3/2019 0100");
        c.execute(tl, new Ui(), new Storage(""));
        assertEquals(other.list.get(0).toString(), tl.list.get(0).toString());
    }
}
