import task.Task;
import task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void sizeTest() {
        TaskList temp = new TaskList();
        temp.addTasks("todo read book");
        assertEquals(1, temp.getSize());
    }

    @Test
    void deleteTest() {
        TaskList temp = new TaskList();
        temp.addTasks("todo read book");
        Task curr = temp.deleteTask(0);
        assertEquals("read book", curr.getName());
    }
}
