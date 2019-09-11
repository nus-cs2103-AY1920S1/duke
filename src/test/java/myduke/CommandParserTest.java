package myduke;

import myduke.command.AddTaskCommand;
import myduke.command.Command;
import myduke.command.CommandParser;
import myduke.command.TerminateSessionCommand;
import myduke.command.DeleteCommand;
import myduke.command.MarkCompletedTaskCommand;
import myduke.command.ListCommand;
import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParserTest {
    @Test
    void testCommand_EmptyOrInvalid() throws DukeException {
        CommandParser parser = new CommandParser();
        assertThrows(DukeInvalidCommandException.class, () -> parser.create(""));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("   "));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("Hello"));
    }

    @Test
    void testCommand_Bye() throws DukeException {
        CommandParser parser = new CommandParser();
        Command cmd = parser.create("bye");
        assertTrue(cmd instanceof TerminateSessionCommand);
        assertTrue(cmd.shouldExit());

        assertThrows(DukeInvalidCommandException.class, () -> parser.create("bye 1"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("bye hi"));
    }

    @Test
    void testCommand_List() throws DukeException {
        CommandParser parser = new CommandParser();
        Command cmd = parser.create("list");
        assertTrue(cmd instanceof ListCommand);
        assertFalse(cmd.shouldExit());

        assertThrows(DukeInvalidCommandException.class, () -> parser.create("list 1"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("list hi"));
    }

    @Test
    void testCommand_Done() throws DukeException {
        CommandParser parser = new CommandParser();
        Command command = parser.create("done 1");
        assertTrue(command instanceof MarkCompletedTaskCommand);

        assertThrows(DukeInvalidCommandException.class, () -> parser.create("done -2"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("done 0"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("done 0 A"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("done 0A"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("done"));
    }

    @Test
    void testCommand_Delete() throws DukeException {
        CommandParser parser = new CommandParser();
        Command command = parser.create("delete 1");
        assertTrue(command instanceof DeleteCommand);

        assertThrows(DukeInvalidCommandException.class, () -> parser.create("delete -2"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("delete 0"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("delete 0 A"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("delete 0A"));
        assertThrows(DukeInvalidCommandException.class, () -> parser.create("delete"));
    }

    @Test
    void testCommand_Add_Todo() throws DukeException {
        CommandParser parser = new CommandParser();
        Command cmd = parser.create("todo sleep eat sleep & sleep");
        assertTrue(cmd instanceof AddTaskCommand);
        assertFalse(cmd.shouldExit());
    }

    @Test
    void testCommand_Add_Deadline() throws DukeException {
        CommandParser parser = new CommandParser();
        Command cmd1 = parser.create("deadline finish project /by 31/08/19 2359");
        assertTrue(cmd1 instanceof AddTaskCommand);
        assertFalse(cmd1.shouldExit());

        Command cmd2 = parser.create("deadline");
        assertTrue(cmd2 instanceof AddTaskCommand);
        assertFalse(cmd2.shouldExit());

        Command cmd3 = parser.create("deadline finish project");
        assertTrue(cmd3 instanceof AddTaskCommand);
        assertFalse(cmd3.shouldExit());

        Command cmd4 = parser.create("deadline /by 31/08/19 2359");
        assertTrue(cmd4 instanceof AddTaskCommand);
        assertFalse(cmd4.shouldExit());

        Command cmd5 = parser.create("deadline finish project /at 31/08/19 2359");
        assertTrue(cmd5 instanceof AddTaskCommand);
        assertFalse(cmd5.shouldExit());
    }

    @Test
    void testCommand_Add_Event() throws DukeException {

        CommandParser parser = new CommandParser();
        Command cmd1 = parser.create("event begin to panic!   /at 31/08/19 2300  ");
        assertTrue(cmd1 instanceof AddTaskCommand);
        assertFalse(cmd1.shouldExit());

        Command cmd2 = parser.create("event begin to panic! 31/08/19 2300  ");
        assertTrue(cmd2 instanceof AddTaskCommand);
        assertFalse(cmd2.shouldExit());

        Command cmd3 = parser.create("event begin to panic!");
        assertTrue(cmd3 instanceof AddTaskCommand);
        assertFalse(cmd3.shouldExit());

        Command cmd4 = parser.create("event /at 31/08/19 2300  ");
        assertTrue(cmd4 instanceof AddTaskCommand);
        assertFalse(cmd4.shouldExit());

        Command cmd5 = parser.create("event begin to panic! /by 31/08/19 2300  ");
        assertTrue(cmd5 instanceof AddTaskCommand);
        assertFalse(cmd5.shouldExit());
    }
}
