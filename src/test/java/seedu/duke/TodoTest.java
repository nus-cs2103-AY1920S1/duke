package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.task.Todo;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    void testToString() {
        assertEquals("[T][✘] description", new Todo("description").toString());
    }
    @Test
    void toSaveString() {
        assertEquals("T | 0 | description", new Todo("description").toSaveString());
    }
}
