package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals("[\u2718] description", task.toString());
    }
}