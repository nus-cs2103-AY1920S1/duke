import duke.exception.*;
import duke.logic.Parser;

import duke.command.DeleteCommand;
import duke.command.PrintListCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    @Test
    public void parseTest() throws EmptyTaskDukeException, InvalidTaskDukeException, InvalidInputDukeException,
            EmptyFindDukeException, EmptyIndexDukeException {
        assertEquals(Parser.parse("list"), new PrintListCommand());
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(1));
    }
}
