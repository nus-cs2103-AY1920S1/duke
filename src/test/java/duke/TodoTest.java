package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {
    @Test
    void constructor_validInput_pass() {
        String description = "Sample todo";
        Todo notDone = new Todo(description);
        Todo done = new Todo(description, true);

        assertFalse(notDone.getIsDone());
        assertTrue(done.getIsDone());
    }

    @Test
    void markAsDone_validInput_pass() {
        Todo testTodo = new Todo("Sample todo");
        testTodo.markAsDone();

        assertTrue(testTodo.getIsDone());
    }
}
