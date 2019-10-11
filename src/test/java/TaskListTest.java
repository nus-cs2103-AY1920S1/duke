import task.Task;
import task.TaskList;
import org.junit.jupiter.api.Test;
import task.TodoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TaskListTest {
    @Test
    void sizeTest() {
        TaskList temp = new TaskList();
        TodoTask myTask = new TodoTask("read book");
        temp.addTasks(myTask);
        assertEquals(1, temp.getSize());
    }

    @Test
    void deleteTest() {
        TaskList temp = new TaskList();
        TodoTask myTask = new TodoTask("read book");
        temp.addTasks(myTask);
        Task curr = temp.deleteTask(1);
        assertEquals("read book", curr.getName());
    }
}
