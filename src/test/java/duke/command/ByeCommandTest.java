package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.StorageStub;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.UiStub;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ByeCommandTest {
    @Test
    public void constructor_correctNumberOfArguments_success() throws Exception {
        Command command = new ByeCommand("Bye");
    }

    @Test
    public void constructor_wrongNumberOfArguments_exceptionThrown() {
        try {
            Command command = new ByeCommand("Bye World");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are too many arguments for this command.", e.getMessage());
        }
    }

    @Test
    public void execute_success() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new UiStub("", (String[] testPrint) -> {
            assertArrayEquals(testPrint, new String[] { "Bye. Hope to see you again soon!" });
        });
        Storage storage = new StorageStub(tasks);

        Command command = new ByeCommand("Bye");
        ui.print(command.execute(tasks, storage));
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new ByeCommand("Bye")).isExit(), true);
    }
}
