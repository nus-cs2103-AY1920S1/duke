import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testListCommand() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void testAddCommand() throws DukeException {
        assertTrue(Parser.parse("todo test") instanceof AddCommand);
    }

    @Test
    public void testDeleteCommand() throws DukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void testDoneCommand() throws DukeException {
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
    }

    @Test
    public void testErrorCommand() throws DukeException {
        assertThrows(DukeException.class, () -> {
            Parser.parse("hi");
        });
    }
}
