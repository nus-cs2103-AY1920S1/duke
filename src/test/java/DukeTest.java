import duke.core.Parser;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Encapsulates a DukeTest object to run JUnit test on Duke.
 */

public class DukeTest {

    @Test
    /**
     * Tests getCommandType() method in the Parser class.
     */ void testGetCommandType() {
        assertEquals("todo", new Parser("todo read book", 5)
                .getCommandType());
    }

    @Test
    /**
     * Tests toString() method in the Todo class.
     */ void testStringConversion() {
        assertEquals("[T][✗]read book", new Todo("read book",
                false).toString());
    }

}