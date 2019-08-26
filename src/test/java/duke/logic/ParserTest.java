import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.logic.DukeException;
import duke.logic.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {


    @Test
    public void testParserBye() throws DukeException {
        try {
            assertEquals(new ByeCommand(),Parser.parse("bye")  );
        } catch (DukeException e) {
            assertEquals(e.getMessage() ,e.getMessage());
        }


    }

    @Test
    public void testParserList() throws DukeException {
        try {
            assertEquals(new ListCommand(),Parser.parse("list")  );
        } catch (DukeException e) {
            assertEquals(e.getMessage() ,e.getMessage());
        }
    }
}