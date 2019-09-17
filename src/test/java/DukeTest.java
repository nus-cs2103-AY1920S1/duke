import duke.command.Command;
import duke.command.ExitCommand;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {

    @Test
    void taskListTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("task1");
        taskList.addTask(task);
        assertEquals(task, taskList.getTaskList().get(0));
        taskList.addTask(new Todo("task2"));
        assertEquals(2, taskList.size());
    }

    @Test
    void exitTest() {
        Command c = new ExitCommand();
        assertTrue(c.isExit());
    }
}