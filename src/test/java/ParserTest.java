
import duke.exception.DukeException;
import duke.util.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parse_incompleteCommand_exceptionThrown() {
        try {
            Parser.parse("hello");
            fail();
        } catch (DukeException e) {
            assertEquals("          ☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    void parse_noDateEvent_exceptionThrown() {
        try {
            Parser.parse("event party");
            fail();
        } catch (DukeException e) {
            assertEquals("     Please enter the time/date!!", e.getMessage());
        }
    }
}
