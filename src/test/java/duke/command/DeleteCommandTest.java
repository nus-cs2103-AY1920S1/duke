package duke.command;

import duke.StorageStub;
import duke.task.TaskImpl;
import duke.task.TaskListStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {
    private static final TaskListStub taskListStub = new TaskListStub();
    private static final StorageStub storageStub = new StorageStub();

    @Test
    void run_validIndex_success() throws IOException {
        String deleteString = new TaskImpl("remove 123").toString();
        List<String> expected = List.of("Noted. I've removed this task:", "  " + deleteString,
                "Now you have " + taskListStub.size() + " tasks in the list.");
        List<String> actual = new DeleteCommand(taskListStub, storageStub)
                .run(new String[]{"delete", "124"});
        assertEquals(expected, actual);
        assertEquals(deleteString, taskListStub.lastAction.toString());
        assertEquals(deleteString, storageStub.stored.get(0));
    }
}
