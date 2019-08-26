package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon() {
        Task task = new Task("description");
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    void getIsDone() {
        Task task = new Task("description");
        assertEquals(false, task.getIsDone());
    }

    @Test
    void getDescription() {
        Task task = new Task("description");
        assertEquals("description", task.getDescription());
    }

    @Test
    void testToString() {
        Task task = new Task("description");
        assertEquals("[✘] description", task.toString());
    }
}