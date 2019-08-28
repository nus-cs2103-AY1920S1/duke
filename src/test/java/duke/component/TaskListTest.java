package duke.component;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private  TaskList taskList;

    @Test
    void addTest() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void deleteAtTest() throws DukeException {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        Task deletedTask = taskList.deleteAt(0);
        assertEquals("read book", deletedTask.getDescription());
    }

    @Test
    void getAtIndexTest() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        assertEquals("read book", taskList.getAtIndex(0).getDescription());
    }

    @Test
    void getSizeTest() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.add(new Deadline("return book", LocalDateTime.now()));
        assertEquals(2, taskList.getSize());
    }

    @Test
    void replace() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.replace(0, new Todo("buy break"));
        assertEquals("buy break", taskList.getAtIndex(0).getDescription());
    }

    @Test
    void ToStringTest() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        String expected = "\t____________________________________________________________" +
                "\n\t Here are the tasks in your list:";

        expected = expected + "\n\t 1. " + taskList.getAtIndex(0).toString();
        expected = expected + "\n\t____________________________________________________________\n";

        assertEquals(expected, taskList.toString());
    }
}