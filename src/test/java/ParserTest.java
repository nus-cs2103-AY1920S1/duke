import kappa.command.ListCommand;
import kappa.command.AddDeadlineCommand;
import kappa.command.AddEventCommand;
import kappa.command.AddToDoCommand;
import kappa.command.ClearCommand;
import kappa.command.DeleteCommand;
import kappa.command.DoneCommand;
import kappa.command.ExitCommand;
import kappa.command.HelpCommand;
import kappa.command.FindCommand;

import kappa.elements.Parser;
import kappa.exception.KappaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Parser.
 */
class ParserTest {

    /**
     * Tests if the commands return the appropriate command type.
     *
     * @throws KappaException Any exception that is thrown from doing any valid commands.
     */
    @Test
    void testCommands() throws KappaException {

        final String command1 = "list";
        final String command2 = "delete 1";
        final String command3 = "bye";
        final String command4 = "help";
        final String command5 = "clear";
        final String command6 = "todo Test";
        final String command7 = "event Test /at Date";
        final String command8 = "deadline Test /by Date";
        final String command9 = "done 1";
        final String command10 = "find lol";
        final String command11 = "todo Test /t #Tag1 #Tag2";
        final String command12 = "event Test2 /at Date /t #Tag1 #Tag2";
        final String command13 = "deadline Test3 /by Date /t #Tag1 #Tag2";

        assertTrue(Parser.parse(command1) instanceof ListCommand);
        assertTrue(Parser.parse(command2) instanceof DeleteCommand);
        assertTrue(Parser.parse(command3) instanceof ExitCommand);
        assertTrue(Parser.parse(command4) instanceof HelpCommand);
        assertTrue(Parser.parse(command5) instanceof ClearCommand);
        assertTrue(Parser.parse(command6) instanceof AddToDoCommand);
        assertTrue(Parser.parse(command7) instanceof AddEventCommand);
        assertTrue(Parser.parse(command8) instanceof AddDeadlineCommand);
        assertTrue(Parser.parse(command9) instanceof DoneCommand);
        assertTrue(Parser.parse(command10) instanceof FindCommand);
        assertTrue(Parser.parse(command11) instanceof AddToDoCommand);
        assertTrue(Parser.parse(command12) instanceof AddEventCommand);
        assertTrue(Parser.parse(command13) instanceof AddDeadlineCommand);
    }
}
