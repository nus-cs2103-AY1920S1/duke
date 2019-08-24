package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

class DukeTest {
    @Test
    void testCreateAndDeleteTasks() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new UiStub();
        Storage storage = new StorageStub();

        Command command;
        command = CommandParser.parse("todo read book");
        command.execute(tasks, ui, storage);
        assertEquals(tasks.getTask(0).getDescription(), "read book");

        command = CommandParser.parse("done 1");
        command.execute(tasks, ui, storage);
        assertTrue(tasks.getTask(0).getIsDone());

        command = CommandParser.parse("delete 1");
        command.execute(tasks, ui, storage);
        assertEquals(tasks.size(), 0);
    }
}
