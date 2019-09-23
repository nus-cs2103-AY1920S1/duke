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

public class EventCommandTest {
    @Test
    public void constructor_validArguments_success() throws Exception {
        Command command1 = new EventCommand("event lunch /at 22/12/2019 18:00");
        Command command2 = new EventCommand("event dinner /at 22/12/2019 18:00 /to 22/12/2019 19:00");
    }

    @Test
    public void constructor_invalidArgumentsFormat_exceptionThrown() {
        try {
            Command command = new EventCommand("event");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of an event cannot be empty.", e.getMessage());
        }
        try {
            Command command = new EventCommand("event /at 22/12/2019");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the date & time for this task is empty.", e.getMessage());
        }
        try {
            Command command = new EventCommand("event eat lunch /at");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the date & time for this task is empty.", e.getMessage());
        }
        try {
            Command command = new EventCommand("event report status");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description or the date & time for this task is empty.", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDatetimeArgument_exceptionThrown() {
        try {
            Command command = new EventCommand("event report /at tonight");
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
                "[E][✘] report (at: 22/12/2019 18:00)",
                "Now you have 1 tasks in the list."
            });
        });

        Command command = new EventCommand("event report /at 22/12/2019 18:00");
        ui.print(command.execute(tasks, storage));
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new EventCommand("event report /at 22/12/2019 18:00")).isExit(), false);
    }
}
