package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    void constructorTest_emptyDescription_exceptionThrown() {
        try {
            new Todo("");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    void stringConversion() throws DukeException {
        Todo todo = new Todo("Test");
        assertEquals("[T][\u2718] Test", todo.toString());
    }

    @Test
    void serialize() throws DukeException {
        Todo todo = new Todo("Test");
        assertEquals("T | 0 | Test", todo.serialize());
    }
}
