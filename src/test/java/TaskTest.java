import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

    @Test
    void getTask() {
        Task task = new Task("test");
        assertEquals("test", task.getTask());
    }

    @Test
    void getStatus() {
        Task task = new Task("test");
        assertEquals(false, task.getStatus());
    }

    @Test
    void markAsDone() {
        Task task = new Task("test");
        task.markAsDone();
        assertTrue(task.getStatus());
    }

    @Test
    void testToString() {
        Task task = new Task("test");
        assertEquals("[✗] " + task.getTask(), task.toString());
    }
}