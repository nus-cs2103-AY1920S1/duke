package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TodoTest {

    @Test
    void constructorTest_emptyDescription_exceptionThrown() {
        try {
            new Todo("");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    void stringConversion() throws DukeException {
        Todo todo = new Todo("Test");
        assertEquals("[T][✘] Test", todo.toString());
    }

    @Test
    void encode() throws DukeException {
        Todo todo = new Todo("Test");
        assertEquals("T | 0 | Test", todo.encode());
    }
}
