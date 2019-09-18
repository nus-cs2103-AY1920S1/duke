package duke.command;

import duke.task.TaskImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import duke.StorageStub;
import duke.task.Task;
import duke.task.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();
    private static final StorageStub storageStub = new StorageStub();

    @Test
    void run_validIndex_success() throws IOException {
        Task doneTask = new TaskImpl("get 123");
        doneTask.markAsDone();
        List<String> expected = List.of("Nice! I've marked this task as done:", "  " + doneTask);
        List<String> actual = new DoneCommand(taskListStub, storageStub)
                .run(new String[]{"done", "124"});
        assertEquals(expected, actual);
        assertEquals(doneTask.toString(), taskListStub.lastAction.toString());
        assertEquals(doneTask.toString(), storageStub.stored.get(0));
    }
}
