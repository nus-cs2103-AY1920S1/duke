package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test1() {
        ArrayList<Task> list = new ArrayList<>();
        ToDo thing = new ToDo("make lunch");
        list.add(thing);
        TaskList tasklist = new TaskList(list);
        assertEquals(thing, tasklist.list.get(0));
    }

    @Test
    public void test2() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasklist = new TaskList();
        assertEquals(list, tasklist.list);
    }

    @Test
    public void test3() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("one"));
        list.add(new ToDo("two"));
        list.add(new ToDo("three"));
        list.add(new ToDo("four"));
        TaskList taskList = new TaskList(list);
        assertEquals(4, taskList.list.size());
    }

    @Test
    public void test4() {
        ArrayList<Task> list = new ArrayList<>();
        Date date = new GregorianCalendar(2019, 8, 30, 16, 0).getTime();
        Event event = new Event("CS2103T lecture", date);
        list.add(event);
        TaskList tasklist = new TaskList(list);
        assertEquals(event, tasklist.list.get(0));
    }

    @Test
    public void test5() {
        ArrayList<Task> list = new ArrayList<>();
        Date date = new GregorianCalendar(2019, 8, 28, 23, 59).getTime();
        Deadline deadline = new Deadline("CS2103T project", date);
        list.add(deadline);
        TaskList tasklist = new TaskList(list);
        assertEquals(deadline, tasklist.list.get(0));
    }
}
