package duke.command;

import duke.task.TaskList;
import duke.task.TaskStub;
import duke.ui.MainWindowStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteCommandTest {

    @Test
    void run_validIndex_taskDeleted() {
        DeleteCommand deleteCommand = new DeleteCommand(
                new String[] {
                        "1"
                });
        MainWindowStub windowStub = new MainWindowStub();
        TaskList testTasks = new TaskList();
        testTasks.addTask(new TaskStub());

        assertEquals(1, testTasks.getSize());
        deleteCommand.run(testTasks, windowStub, null);
        assertEquals(0, testTasks.getSize());
        assertTrue(windowStub.getMessages().contains("Noted. I've removed this task:"));
    }

    @Test
    void validate_invalidIndex_throwsDukeInvalidArgumentException() {
        DeleteCommand deleteCommand = new DeleteCommand(
                new String[] {
                        "11"
                });
        DeleteCommand deleteCommand2 = new DeleteCommand(
                new String[] {
                        "Nan"
                });
        TaskList testTasks = new TaskList();
        testTasks.addTask(new TaskStub());

        assertThrows(DukeInvalidArgumentException.class,
                () -> deleteCommand.validate(testTasks, null, null));
        assertThrows(DukeInvalidArgumentException.class,
                () -> deleteCommand2.validate(testTasks, null, null));
    }

}
