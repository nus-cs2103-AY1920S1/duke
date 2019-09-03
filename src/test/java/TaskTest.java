import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskType() {
        Task testTask = new Task("complete testing", "todo", false);
        assertEquals(testTask.getTaskType(), "[T]");
    }

    @Test
    public void testTaskMarkAsDone() {
        Task testTask = new Task("complete testing", "todo", false);
        testTask.markAsDone();
        assertEquals(testTask.getStatusIcon(), "[\u2713]");
    }
}
