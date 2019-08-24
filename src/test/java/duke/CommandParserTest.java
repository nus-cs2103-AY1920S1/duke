package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.parser.CommandParser;
import org.junit.jupiter.api.Test;

class CommandParserTest {
    @Test
    void testBye_isExit() throws DukeException {
        Command byeCommand = CommandParser.parse("bye");
        assertTrue(byeCommand.isExit());
    }

    @Test
    void testList() throws DukeException {
        Command command = CommandParser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testDone() throws DukeException {
        Command command = CommandParser.parse("done 1");
        assertTrue(command instanceof DoneCommand);

        assertThrows(InvalidInputDukeException.class,
                () -> CommandParser.parse("done"));
    }

    @Test
    void testDelete() throws DukeException {
        Command command = CommandParser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);

        assertThrows(InvalidInputDukeException.class,
                () -> CommandParser.parse("delete"));
    }

    @Test
    void testAdd_Todo_ValidInput() throws DukeException {
        Command command = CommandParser.parse("todo do homework");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testAdd_Deadline_ValidInput() throws DukeException {
        Command command = CommandParser.parse("deadline finish project /by 31/12/2019 2359");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testAdd_Event_ValidInput() throws DukeException {
        Command command = CommandParser.parse("event open presents /at 25/12/2019 0000");
        assertTrue(command instanceof AddCommand);
    }
}
