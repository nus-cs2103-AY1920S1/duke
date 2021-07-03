import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    private static final String CIRCLE = "O";   
    private static final String CROSS = "X";    

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
        assertEquals("[" + CIRCLE + "] This is a Task.", task.toString());
    }


}