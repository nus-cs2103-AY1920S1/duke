import commands.Command;
import commands.AddCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.DeleteCommand;
import commands.FindCommand;

import duke.Parser;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testBye() throws DukeException {
        Command cmd = Parser.parse("bye");
        assertEquals(true, cmd instanceof ExitCommand);
        assertEquals(cmd.getFullCommand(), "bye");
        assertEquals(cmd.isExit(), true);
    }

    @Test
    public void testDone() throws DukeException {
        Command cmd = Parser.parse("done 5");
        assertEquals(true, cmd instanceof DoneCommand);
        assertEquals(cmd.getFullCommand(), "done 5");
        assertEquals(cmd.isExit(), false);
    }

    @Test
    public void testDelete() throws DukeException {
        Command cmd = Parser.parse("delete 3");
        assertEquals(true, cmd instanceof DeleteCommand);
        assertEquals(cmd.getFullCommand(), "delete 3");
        assertEquals(cmd.isExit(), false);
    }

    @Test
    public void testFind() throws DukeException {
        Command cmd = Parser.parse("find ball");
        assertEquals(true, cmd instanceof FindCommand);
        assertEquals(cmd.getFullCommand(), "find ball");
        assertEquals(cmd.isExit(), false);
    }

    @Test
    public void testToDo() throws DukeException {
        Command cmd = Parser.parse("todo homework");
        assertEquals(true, cmd instanceof AddCommand);
        assertEquals(cmd.getFullCommand(), "todo homework");
        assertEquals(cmd.isExit(), false);
    }

    @Test
    public void testDeadline() throws DukeException {
        Command cmd = Parser.parse("deadline submit homework /by 24/03/2021");
        assertEquals(true, cmd instanceof AddCommand);
        assertEquals(cmd.getFullCommand(), "deadline submit homework /by 24/03/2021");
        assertEquals(cmd.isExit(), false);
    }

    @Test
    public void testEvent() throws DukeException {
        Command cmd = Parser.parse("event party with Daniel /at 27/08/2019");
        assertEquals(true, cmd instanceof AddCommand);
        assertEquals(cmd.getFullCommand(), "event party with Daniel /at 27/08/2019");
        assertEquals(cmd.isExit(), false);
    }
}
