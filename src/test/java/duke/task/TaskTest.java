package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void test_markAsDone() {
        Task task = new TaskStub("Testing");
        assertEquals(0, task.getStatusCode());
        task.markAsDone();
        assertEquals(1, task.getStatusCode());
    }
}