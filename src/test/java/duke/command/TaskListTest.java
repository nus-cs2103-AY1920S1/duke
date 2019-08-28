package duke.command;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void taskList_invalidCommand_exceptionThrown() {
       TaskList taskList = new TaskList();
       taskList.addTask(new Todo("Do this thing",  false, ""));
       assertEquals(1, taskList.getSize());
    }
}