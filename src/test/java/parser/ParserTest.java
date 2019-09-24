package parser;

import command.ByeCommand;
import command.Command;
import exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_byeCommand_success() {
        try {
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
        } catch (DukeException ex) {
            System.out.println("Unexpected exception caught in parse_byeCommand_success:" + ex.getMessage());
            fail();
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            assertTrue(Parser.parse("this is an illegal command") instanceof Command);
            fail();
        } catch (DukeException ex) {
            assertEquals("Invalid command! Try the commands: bye, list, done, todo, deadline, event, find, delete or"
                    + " reschedule and their respective formats!", ex.getMessage());
        }
    }
}