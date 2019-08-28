import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    private final String TICK = "\u2713";
    private final String CROSS = "\u2717";

    @Test
    public void fieldVariableTest() {
        Task task = new Task("This is a Task.");
        assertFalse(task.hasCompleted());
        task.setCompleted(true);
        assertTrue(task.hasCompleted());
    }

    @Test
    public void toStringTest() {
        Task task = new Task("This is a Task.");
        assertEquals("[" + CROSS + "] This is a Task.", task.toString());
        task.setCompleted(true);    
        assertEquals("[" + TICK + "] This is a Task.", task.toString());
    }


}