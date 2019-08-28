package duke.command;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests TaskList class
 */
class TaskListTest {
    /**
     * Tests if TaskList successfully add a new Task
     */
    @Test
    public void taskList_invalidCommand_exceptionThrown() {
       TaskList taskList = new TaskList();
       taskList.addTask(new Todo("Do this thing",  false, ""));
       assertEquals(1, taskList.getSize());
    }
}