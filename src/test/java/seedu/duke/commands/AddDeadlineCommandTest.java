package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.TaskManagerStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadlineCommandTest {
    @Test
    public void addDeadlineTask_increaseNumberOfTasks() throws Exception {
        String description = "example deadlineTask";
        String deadlineInfo = "next Monday";
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(description, deadlineInfo);

        TaskManagerStub taskManager = new TaskManagerStub("data/test.txt");
        int initialNumberOfTasks = taskManager.getNumberOfTasks();
        addDeadlineCommand.execute(taskManager);

        assertEquals(initialNumberOfTasks + 1, taskManager.getNumberOfTasks());
    }
}
