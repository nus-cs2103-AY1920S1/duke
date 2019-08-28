import org.junit.jupiter.api.Test;
import duke.io.Parser;
import duke.command.DukeMissingCommandException;
import duke.command.DeleteTaskCommand;
import duke.command.CompleteTaskCommand;
import duke.command.DukeIncorrectParameterTypeException;

import duke.frontend.UserInterface;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test functionality of certain methods in the UserInterface class
 */
public class UserInterfaceTest {
    UserInterface ui = new UserInterface();

    /**
     * Test whether an exception is thrown when the wrong type of arguement is used for the commands to delete a task
     * or mark a task as done
     */
    @Test
    public void executeCommand_incorrectArgumentType_exceptionThrown() {
        assertThrows(DukeIncorrectParameterTypeException.class,
                () -> ui.executeCommand(new DeleteTaskCommand("a")));
        assertThrows(DukeIncorrectParameterTypeException.class,
                () -> ui.executeCommand(new CompleteTaskCommand("a")));
    }

    /**
     * Test whether an exception is thrown when an empty command is given
     */
    @Test
    public void executeCommand_noInput_exceptionThrown() {
        assertThrows(DukeMissingCommandException.class,
                () -> ui.executeCommand(Parser.parseAsCommand("")));
    }

}