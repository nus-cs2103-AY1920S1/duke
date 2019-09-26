import task.Task;
import task.TaskList;
import task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void sizeTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getTaskTest() {
        Task todo = new Todo("return book");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals("[T][✘] return book", taskList.getTask(0).toString());
    }
}
