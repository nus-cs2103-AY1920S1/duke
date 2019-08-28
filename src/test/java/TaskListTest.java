import duke.TaskList;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void taskListTest() {
        Task todo = new Todo("Todo Description");
        TaskList tasklist = new TaskList(new ArrayList<>());
        assertEquals(0, tasklist.getTaskSize());
        tasklist.addTask(todo);
        assertEquals(1, tasklist.getTaskSize());
        assertEquals(todo, tasklist.getTaskByIndex(1));
        tasklist.removeTaskByIndex(1);
        assertEquals(0, tasklist.getTaskSize());
    }

}