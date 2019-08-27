import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    @DisplayName("Encoded string of TodoTask is correct")
    public void testToEncodedString() {
        // Test setup
        TodoTask task = new TodoTask("Go eat a banana");
        task.complete();

        assertEquals(
            "T | 1 | Go eat a banana",
            task.toEncodedString()
        );
    }

    @Test
    @DisplayName("String representation of TodoTask is correct")
    public void testToString() {
        // Test setup
        TodoTask task = new TodoTask("Go fly a kite");
        task.complete();
        
        assertEquals(
            "[T][V] Go fly a kite",
            task.toString()
        );
    }
}
