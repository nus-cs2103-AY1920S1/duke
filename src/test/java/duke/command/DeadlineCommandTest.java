package duke.command;

import duke.storage.StorageStub;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.UiStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void constructor_validArguments_success() throws Exception {
        Command command = new DeadlineCommand("deadline report /by 22/12/2019 18:00");
    }

    @Test
    public void constructor_invalidArgumentsFormat_exceptionThrown() {
        try {
            Command command = new DeadlineCommand("deadline");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
        try {
            Command command = new DeadlineCommand("deadline /by 22/12/2019");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the deadline for this task is empty.", e.getMessage());
        }
        try {
            Command command = new DeadlineCommand("deadline report status /by");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the deadline for this task is empty.", e.getMessage());
        }
        try {
            Command command = new DeadlineCommand("deadline report status");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the deadline for this task is empty.", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDatetimeArgument_exceptionThrown() {
        try {
            Command command = new DeadlineCommand("deadline report /by tonight");
        } catch (DukeException e) {
            assertEquals("Invalid Date Time format! Format should be: dd/MM/yyyy HH:mm", e.getMessage());
        }
    }

    @Test
    public void execute_success() throws Exception {
        TaskList tasks = new TaskList();
        Storage storage = new StorageStub(tasks);
        Ui ui = new UiStub("", (String[] testPrint) -> {
            assertArrayEquals(testPrint, new String[] {
                "Got it. I've added this task:",
                "[D][✘] report (by: 22/12/2019 18:00)",
                "Now you have 1 tasks in the list."
            });
        });

        Command command = new DeadlineCommand("deadline report /by 22/12/2019 18:00");
        ui.print(command.execute(tasks, storage));
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new DeadlineCommand("deadline report /by 22/12/2019 18:00")).isExit(), false);
    }
}
