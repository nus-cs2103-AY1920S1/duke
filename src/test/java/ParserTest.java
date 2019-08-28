import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.util.DukeException;
import duke.util.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    @Test
    public void inputParsing_validInputs_success() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("event lecture /at 28/08/2019 1600") instanceof AddCommand);
    }

    @Test
    public void inputParsing_invalidInputs_exceptionThrown() {
        try {
            assertTrue(Parser.parse("event lecture 28/08/2019 1600") instanceof AddCommand);
            fail();
        } catch (DukeException e) {
            assertEquals("Please follow the format:\n     event <description> /at <DD/MM/YYYY HHMM>",
                    e.getMessage());
        }
    }
}
