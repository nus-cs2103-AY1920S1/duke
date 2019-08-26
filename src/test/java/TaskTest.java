import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Task.
 */
public class TaskTest {
    /**
     * Creates a new task, and checks whether its toString() method gives the correct output.
     */
    @Test
    public void createTaskTest() {
        Task newTask = new Task("Sample Task", true);
        assertEquals("[\u2713] Sample Task", newTask.toString());
    }
}