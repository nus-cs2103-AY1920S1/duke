package duke.command;

import duke.TestUtils;
import duke.task.TaskList;
import duke.task.TaskStub;
import duke.ui.MainWindowStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListCommandTest {
    @Test
    void execute_validArguments_success() {
        MainWindowStub windowStub = new MainWindowStub();
        TaskList testTasks = new TaskList();
        TaskStub testTaskStub = new TaskStub(TestUtils.generateRandomString(10,20), "");
        testTasks.addTask(testTaskStub);
        ListCommand listCommand = new ListCommand(new String[0]);
        assertDoesNotThrow(() -> listCommand.execute(testTasks, windowStub, null));

        assertTrue(windowStub.getMessages().contains(testTaskStub.getStatusText()));
    }

    @Test
    void execute_invalidArguments_throwsDukeInvalidArgumentException() {
        ListCommand listCommand = new ListCommand(new String[1]);
        assertThrows(DukeInvalidArgumentException.class,
                () -> listCommand.execute(null, null, null));
    }
}
