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
import myduke.exception.DukeEmptyDescriptionException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    @Test
    void testCommand_EmptyOrInvalid() throws DukeException {
        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create(""));
        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("   "));
        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("Hello"));
    }

    @Test
    void testCommand_Bye() throws DukeException {
        Command cmd = CommandParser.create("bye");
        assertTrue(cmd instanceof TerminateSessionCommand);
        assertTrue(cmd.shouldExit());

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("bye 1"));
        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("bye hi"));
    }

    @Test
    void testCommand_List() throws DukeException {
        Command cmd = CommandParser.create("list");
        assertTrue(cmd instanceof ListCommand);
        assertFalse(cmd.shouldExit());

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("list 1"));
        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("list hi"));
    }

    @Test
    void testCommand_Done() throws DukeException {
        Command command = CommandParser.create("done 1");
        assertTrue(command instanceof MarkCompletedTaskCommand);

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("done -2"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("done 0"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("done 0 A"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("done 0A"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("done"));
    }

    @Test
    void testCommand_Delete() throws DukeException {
        Command command = CommandParser.create("delete 1");
        assertTrue(command instanceof DeleteCommand);

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("delete -2"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("delete 0"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("delete 0 A"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("delete 0A"));

        assertThrows(DukeInvalidCommandException.class,
                () -> CommandParser.create("delete"));
    }

    @Test
    void testCommand_Add_Todo() throws DukeException {
        Command cmd = CommandParser.create("todo sleep eat sleep & sleep");
        assertTrue(cmd instanceof AddTaskCommand);
        assertFalse(cmd.shouldExit());
    }

    @Test
    void testCommand_Add_Deadline() throws DukeException {
        Command cmd1 = CommandParser.create("deadline finish project /by 31/08/19 2359");
        assertTrue(cmd1 instanceof AddTaskCommand);
        assertFalse(cmd1.shouldExit());

        Command cmd2 = CommandParser.create("deadline");
        assertTrue(cmd2 instanceof AddTaskCommand);
        assertFalse(cmd2.shouldExit());

        Command cmd3 = CommandParser.create("deadline finish project");
        assertTrue(cmd3 instanceof AddTaskCommand);
        assertFalse(cmd3.shouldExit());

        Command cmd4 = CommandParser.create("deadline /by 31/08/19 2359");
        assertTrue(cmd4 instanceof AddTaskCommand);
        assertFalse(cmd4.shouldExit());

        Command cmd5 = CommandParser.create("deadline finish project /at 31/08/19 2359");
        assertTrue(cmd5 instanceof AddTaskCommand);
        assertFalse(cmd5.shouldExit());
    }

    @Test
    void testCommand_Add_Event() throws DukeException {

        Command cmd1 = CommandParser.create("event begin to panic!   /at 31/08/19 2300  ");
        assertTrue(cmd1 instanceof AddTaskCommand);
        assertFalse(cmd1.shouldExit());

        Command cmd2 = CommandParser.create("event begin to panic! 31/08/19 2300  ");
        assertTrue(cmd2 instanceof AddTaskCommand);
        assertFalse(cmd2.shouldExit());

        Command cmd3 = CommandParser.create("event begin to panic!");
        assertTrue(cmd3 instanceof AddTaskCommand);
        assertFalse(cmd3.shouldExit());

        Command cmd4 = CommandParser.create("event /at 31/08/19 2300  ");
        assertTrue(cmd4 instanceof AddTaskCommand);
        assertFalse(cmd4.shouldExit());

        Command cmd5 = CommandParser.create("event begin to panic! /by 31/08/19 2300  ");
        assertTrue(cmd5 instanceof AddTaskCommand);
        assertFalse(cmd5.shouldExit());
    }
}
