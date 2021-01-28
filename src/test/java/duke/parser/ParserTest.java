package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import duke.dukeexception.DukeException;



public class ParserTest {
    @Test
    public void parse_unrecognizedCommand_exceptionThrown() {
        try {
            new Parser().parse("FE92D6ECB6AEC0000AC130DEBE41577B701ABACC96E63EF139564B999B284B33");
            fail();
        } catch (DukeException de) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", de.getMessage());
        }
    }

    @Test
    public void parse_deadlineTooFewArguments_exceptionThrown() {
        try {
            new Parser().parse("deadline");
            fail();
        } catch (DukeException de) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.", de.getMessage());
        }
    }

    @Test
    public void parse_eventTooFewArguments_exceptionThrown() {
        try {
            new Parser().parse("event");
            fail();
        } catch (DukeException de) {
            assertEquals("☹ OOPS!!! The description of a event cannot be empty.", de.getMessage());
        }
    }

}
