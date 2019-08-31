package seedu.duke.util;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testOutputString() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addToList(new Todo("This is number 1"));
        taskList.addToList(new Deadline("This is number 2", "12/12/1997 1800"));
        taskList.addToList(new Event("This is number 3", "12/12/1997 1900"));
        assertEquals("1. [T] [-] This is number 1\n"
                + "2. [D] [-] This is number 2 (by: 12/12/1997 1800 )\n"
                + "3. [E] [-] This is number 3 (at: 12/12/1997 1900 )\n", taskList.toString());
    }

    @Test
    public void testGetTask() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addToList(new Todo("This is number 1"));
        taskList.addToList(new Deadline("This is number 2", "12/12/1997 1800"));
        taskList.addToList(new Event("This is number 3", "12/12/1997 1900"));
        assertEquals("[T] [-] This is number 1", taskList.getTask(1).toString());
        assertEquals("[D] [-] This is number 2 (by: 12/12/1997 1800 )", taskList.getTask(2).toString());
        assertEquals("[E] [-] This is number 3 (at: 12/12/1997 1900 )", taskList.getTask(3).toString());
    }

}
