import commands.Command;
import commands.AddCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.DeleteCommand;
import commands.FindCommand;

import duke.Duke;
import duke.Parser;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testBye() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("bye", duke);
        assertTrue(cmd instanceof ExitCommand);
        assertEquals("bye", cmd.getCommandArr()[0]);
        assertTrue(duke.getShouldExitProgram());
    }

    @Test
    void testDone() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("done 5", duke);
        assertTrue(cmd instanceof DoneCommand);
        assertEquals("done 5", cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

    @Test
    void testDelete() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("delete 3", duke);
        assertTrue(cmd instanceof DeleteCommand);
        assertEquals("delete 3", cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

    @Test
    void testFind() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("find ball", duke);
        assertTrue(cmd instanceof FindCommand);
        assertEquals("find ball", cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

    @Test
    void testToDo() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("todo homework", duke);
        assertTrue(cmd instanceof AddCommand);
        assertEquals("todo homework", cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

    @Test
    void testDeadline() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("deadline submit homework /by 24/03/2021", duke);
        assertTrue(cmd instanceof AddCommand);
        assertEquals("deadline submit homework /by 24/03/2021",
                cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

    @Test
    void testEvent() throws DukeException {
        Duke duke = new Duke();
        Command cmd = Parser.parse("event party with Daniel /at 27/08/2019", duke);
        assertTrue(cmd instanceof AddCommand);
        assertEquals("event party with Daniel /at 27/08/2019",
                cmd.getCommandArr()[0] + " " + cmd.getCommandArr()[1]);
        assertFalse(duke.getShouldExitProgram());
    }

}
