package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dto.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void todo() {
        Todo todo = new Todo("buy bread");
        todo.setDescription("buy test");
        todo.setErrorMsg("some test");
        assertEquals(false, todo.getIsDone());

        todo.setDone(true);

        assertEquals("buy test", todo.getDescription());
        assertEquals("some test", todo.getErrorMsg());
        assertEquals(true, todo.getIsDone());
    }
}
