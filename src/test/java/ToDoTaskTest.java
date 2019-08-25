import duke.task.Task;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoTaskTest {

    @Test
    public void dummyTest() {
        Task task = new ToDoTask("Eat Food", false);
        assertTrue(task.toString().equals("[T][N] Eat Food"));
        assertFalse(task.isCompleted);
    }

}
