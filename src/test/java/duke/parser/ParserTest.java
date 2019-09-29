package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    public void parseList_validCommand_success() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (DukeException ex) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void parseTodo_validCommand_success() {
        try {
            assertTrue(Parser.parse("todo run") instanceof AddCommand);
        } catch (DukeException ex) {
            fail("Unexpected exception");
        }
    }
}
