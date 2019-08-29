import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskType;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getType() {
        Task task = new Task("return book", TaskType.TODO);
        assertEquals(TaskType.TODO, task.getType());
    }

    @Test
    void getDescription() {
        Task task = new Task("description", TaskType.TODO);
        assertEquals("description", task.getDescription());
    }

    @Test
    void getStatusIcon() {
        Task task = new Task("task", TaskType.TODO);
        assertEquals("[\u2718]", task.getStatusIcon());
    }

    @Test
    void getTypeIcon() {
        Task task = new Task("task", TaskType.TODO);
        assertEquals("[T]", task.getTypeIcon());
    }
}