import duke.DukeException;
import org.junit.jupiter.api.Test;
import duke.io.Parser;
import duke.command.DukeMissingCommandException;
import duke.command.DeleteTaskCommand;
import duke.command.CompleteTaskCommand;
import duke.command.DukeIncorrectParameterTypeException;

import duke.frontend.UserInterface;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class UserInterfaceTest {

    UserInterface ui = new UserInterface();

    @Test
    public void executeCommand_incorrectArgumentType_exceptionThrown() {
        assertThrows(
                DukeIncorrectParameterTypeException.class,
                () -> ui.executeCommand(new DeleteTaskCommand("a")));
        assertThrows(
                DukeIncorrectParameterTypeException.class,
                () -> ui.executeCommand(new CompleteTaskCommand("a")));
    }

    @Test
    public void readInput_blankInput_exceptionThrown() {
        assertThrows(
                DukeMissingCommandException.class,
                () -> ui.executeCommand(Parser.parseAsCommand("")));
    }

}