package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {


    @Test
    public void testIsExit() {
        assertEquals(false, new DeadlineCommand("deadline test".split("\\s+")).isExit());
        assertEquals(false, new DeadlineCommand("deadline test /by ".split("\\s+")).isExit());
        assertEquals(false, new DeadlineCommand("deadline test /by 20/12/2019 1800".split("\\s+")).isExit());
    }

    @Test
    public void testExecute() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/duke.txt");
        assertEquals(false, new DeadlineCommand("deadline test".split("\\s+")).execute(new TaskList(), ui, storage));
        assertEquals(false, new DeadlineCommand("deadline test /by ".split("\\s+")).execute(new TaskList(), ui, storage));
        assertEquals(false, new DeadlineCommand("deadline test /by 20/12/2019".split("\\s+")).execute(new TaskList(), ui, storage));
        assertEquals(true, new DeadlineCommand("deadline test /by 20/12/2019 1800".split("\\s+")).execute(new TaskList(), ui, storage));
    }
}
