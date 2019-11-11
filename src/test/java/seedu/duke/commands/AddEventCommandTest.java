package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.TaskManagerStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventCommandTest {
    @Test
    public void addEventTask_increaseNumberOfTasks() throws Exception {
        String description = "example eventTask";
        String eventInfo = "random location";
        AddEventCommand addEventCommand = new AddEventCommand(description, eventInfo);

        TaskManagerStub taskManager = new TaskManagerStub("data/test.txt");
        int initialNumberOfTasks = taskManager.getNumberOfTasks();
        addEventCommand.execute(taskManager);

        assertEquals(initialNumberOfTasks + 1, taskManager.getNumberOfTasks());
    }
}
