package tasks;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_todoTask_pass() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("A sample todo task.");
        taskList.addTask(todo);
        assertEquals(taskList.getTask(0), todo);
    }
}
