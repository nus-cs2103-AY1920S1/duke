package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.TaskManagerStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void deleteTaskWithID_decreaseNumberOfTasks() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(123);
        TaskManagerStub taskManagerStub = new TaskManagerStub("data/test.txt");

        int numberOfTasks = taskManagerStub.getNumberOfTasks();
        deleteCommand.execute(taskManagerStub);
        assertEquals(numberOfTasks - 1, taskManagerStub.getNumberOfTasks());
    }
}
