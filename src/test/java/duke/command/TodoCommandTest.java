package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.StorageStub;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.UiStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void constructor_validArguments_success() throws Exception {
        Command command = new TodoCommand("todo write report");
    }

    @Test
    public void constructor_invalidArgumentsFormat_exceptionThrown() {
        try {
            Command command = new TodoCommand("todo");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void execute_success() throws Exception {
        TaskList tasks = new TaskList();
        Storage storage = new StorageStub(tasks);
        Ui ui = new UiStub("", (String[] testPrint) -> {
            assertArrayEquals(testPrint, new String[] {
                "Got it. I've added this task:",
                "[T][✘] write report",
                "Now you have 1 tasks in the list."
            });
        });

        Command command = new TodoCommand("todo write report");
        ui.print(command.execute(tasks, storage));
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new TodoCommand("deadline report /by 22/12/2019 18:00")).isExit(), false);
    }
}
