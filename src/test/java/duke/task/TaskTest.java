package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getStatusIcon_taskWithDescription_xUnicodeReturned() {
        Task task = new Task("description");
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    void getIsDone_taskWithDescription_falseReturned() {
        Task task = new Task("description");
        assertEquals(false, task.getIsDone());
    }

    @Test
    void getDescription_taskWithDescription_sameDescriptionReturned() {
        Task task = new Task("description");
        assertEquals("description", task.getDescription());
    }

    @Test
    void toString_taskWithDescription_taskStringReturned() {
        Task task = new Task("description");
        assertEquals("[\u2718] description", task.toString());
    }
}