package duke.command;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TaskStub;
import duke.task.TaskTestConstants;
import duke.task.TodoTask;
import duke.ui.MainWindowStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ArchiveCommandTest {
    @Test
    void validate_invalidArguments_throwsDukeInvalidArgumentException() {
        TaskList tasks = new TaskList();

        assertThrows(DukeInvalidArgumentException.class, () -> {
            ArchiveCommand archiveCommand = new ArchiveCommand(new String[] { "randomText" });
            archiveCommand.validate(null, null, null);
        });

        assertThrows(DukeInvalidArgumentException.class, () -> {
            ArchiveCommand archiveCommand = new ArchiveCommand(new String[0]);
            archiveCommand.validate(tasks, null, null);
        });
    }

    @Test
    void validate_validArguments_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub("random desc", "random timing"));

        ArchiveCommand archiveCommand = new ArchiveCommand(new String[0]);
        assertDoesNotThrow(() -> archiveCommand.validate(tasks, null, null));
    }
}
