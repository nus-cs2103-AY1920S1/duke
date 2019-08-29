package seedu.duke.util;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testParseTodo() throws DukeException {
        String[] testNormal = new String[] {"todo", "Hello"};
        String[] testThrow = new String[] {"todo"};
        assertEquals("Hello", Parser.parseTodo(testNormal));
        assertThrows(DukeException.class, () -> Parser.parseTodo(testThrow));
    }

}
