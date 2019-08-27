package duke.task;

import duke.exception.IllegalDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void testStringConversion() throws IllegalDescriptionException {
        assertEquals("[T][\u2718] test", new ToDo("test").toString());
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        try {
            assertEquals("", new ToDo("").toString());
            fail();
        } catch (IllegalDescriptionException e) {
            assertEquals("The description of a todo cannot be empty.",e.getMessage());
        }
    }
}