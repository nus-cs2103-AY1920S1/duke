
import duke.exception.DukeException;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() throws DukeException {
        assertEquals("[T][X][M] test", new Todo("test").toString());
    }

    @Test
    public void testToText() throws DukeException {
        assertEquals("T|0|M|test", new Todo("test").toText());
    }
}