package duke.parser;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RedoCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CommandParserTest {

    @Test
    void parse_exit() throws DukeException {
        assertTrue(CommandParser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_list() throws DukeException {
        assertTrue(CommandParser.parse("list") instanceof ListCommand);
    }

    @Test
    void parse_done() throws DukeException {
        assertTrue(CommandParser.parse("done 1") instanceof CompleteCommand);
    }

    @Test
    void parse_delete() throws DukeException {
        assertTrue(CommandParser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    void parse_addWithValidInput_success() throws DukeException {
        assertTrue(CommandParser.parse("todo Test") instanceof AddCommand);
        assertTrue(CommandParser.parse("deadline Test /by 01/01/2019 0700") instanceof AddCommand);
        assertTrue(CommandParser.parse("event Test /at 01/01/2019 0700 1500") instanceof AddCommand);
    }

    @Test
    void parse_find() throws DukeException {
        assertTrue(CommandParser.parse("find keyWord") instanceof FindCommand);
    }

    @Test
    void parse_clear() throws DukeException {
        assertTrue(CommandParser.parse("clear") instanceof ClearCommand);
    }

    @Test
    void parse_undo() throws DukeException {
        assertTrue(CommandParser.parse("undo") instanceof UndoCommand);
    }

    @Test
    void parse_redo() throws DukeException {
        assertTrue(CommandParser.parse("redo") instanceof RedoCommand);
    }

    @Test
    void parse_unknownCommand_exceptionThrown() {
        try {
            CommandParser.parse("Random command");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

}