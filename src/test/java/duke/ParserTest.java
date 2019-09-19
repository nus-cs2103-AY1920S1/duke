package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void parseTodo_validInput_pass() throws DukeException {
        String input = "todo buy eggs";
        assertEquals("buy eggs", Parser.parse(input).getParams());
    }
}
