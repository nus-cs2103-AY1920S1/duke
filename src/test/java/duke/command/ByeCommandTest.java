package duke.command;

import duke.ui.MainWindowStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByeCommandTest {
    @Test
    void execute_validArguments_success() {
        MainWindowStub windowStub = new MainWindowStub();
        ByeCommand byeCommand = new ByeCommand(new String[0]);
        assertDoesNotThrow(() -> byeCommand.execute(null, windowStub, null));

        assertEquals(windowStub.getMessages(), "Bye. Hope to see you again soon!");
    }

    @Test
    void execute_invalidArguments_throwsDukeInvalidArgumentException() {
        ByeCommand byeCommand = new ByeCommand(new String[1]);
        assertThrows(DukeInvalidArgumentException.class,
                () -> byeCommand.execute(null, null, null));
    }
}
