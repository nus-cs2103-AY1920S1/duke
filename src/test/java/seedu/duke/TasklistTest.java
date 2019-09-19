package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {

    @Test
    public void getTaskTest() throws ParseException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("Workout"));
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("3/12/1312 0145");
        Event eventTask = new Event("music fest", date);
        list.add(eventTask);
        TaskList taskList = new TaskList(list);
        assertEquals(eventTask, taskList.getTask(1));
    }

    @Test
    public void getSizeTest() throws ParseException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("Workout"));
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("3/12/1312 0145");
        Event eventTask = new Event("music fest", date);
        list.add(eventTask);
        TaskList taskList = new TaskList(list);
        assertEquals(2, taskList.getSize());
    }
}
