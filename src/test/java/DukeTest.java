import duke.command.Command;
import duke.command.ExitCommand;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Storage;
import duke.ui.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private Storage storage;
    private TaskList taskList;


    @Test
    public void taskListTest() {
        taskList = new TaskList();
        Task task = new Todo("task1");
        taskList.addTask(task);
        assertEquals(task, taskList.getTaskList().get(0));
        taskList.addTask(new Todo("task2"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void exitTest() {
        Command c = new ExitCommand();
        assertEquals(true, c.isExit());
    }
}