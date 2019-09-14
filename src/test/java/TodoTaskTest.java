import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import duke.task.TodoTask;

public class TodoTaskTest {
    @Test
    @DisplayName("Encoded string of TodoTask is correct")
    public void testToEncodedString() {
        // Test setup
        TodoTask task = new TodoTask("Go eat a banana", new ArrayList<String>());
        task.complete();

        assertEquals(
            "T | 1 |  | Go eat a banana",
            task.toEncodedString()
        );
    }

    @Test
    @DisplayName("String representation of TodoTask is correct")
    public void testToString() {
        // Test setup
        TodoTask task = new TodoTask("Go fly a kite", new ArrayList<String>());
        task.complete();
        
        assertEquals(
            "[T][V] Go fly a kite",
            task.toString()
        );
    }
}
