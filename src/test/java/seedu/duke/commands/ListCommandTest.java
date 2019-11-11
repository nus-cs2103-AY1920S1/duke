package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.TaskManagerStub;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.ToDoTask;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void listTasksDefault_orderedByCreatedTime() throws Exception {
        List<Task> dummyTasks = new ArrayList<>();

        Task dummyA = new ToDoTask("1st created, 1st updated");
        Thread.sleep(1000);
        dummyTasks.add(dummyA);

        Task dummyB = new ToDoTask("2nd created, 2nd updated");
        Thread.sleep(1000);
        dummyTasks.add(dummyB);

        Task dummyC = new ToDoTask("3rd created, 3rd updated");
        dummyTasks.add(dummyC);

        TaskManagerStub taskManagerStub = new TaskManagerStub("data/test.txt");
        taskManagerStub.setTasks(dummyTasks);

        String expectedResponse = "Here are the tasks in your list:\n";
        expectedResponse += "1. " + dummyC + "\n";
        expectedResponse += "2. " + dummyB + "\n";
        expectedResponse += "3. " + dummyA + "\n";

        ListCommand listCommand = new ListCommand();
        CommandResult result = listCommand.execute(taskManagerStub);

        assertEquals(expectedResponse, result.getFeedback());
    }

    @Test
    public void listTasksByCreatedTime() throws Exception {
        List<Task> dummyTasks = new ArrayList<>();

        Task dummyA = new ToDoTask("1st created, 1st updated");
        Thread.sleep(1000);
        dummyTasks.add(dummyA);

        Task dummyB = new ToDoTask("2nd created, 2nd updated");
        Thread.sleep(1000);
        dummyTasks.add(dummyB);

        Task dummyC = new ToDoTask("3rd created, 3rd updated");
        dummyTasks.add(dummyC);

        TaskManagerStub taskManagerStub = new TaskManagerStub("data/test.txt");
        taskManagerStub.setTasks(dummyTasks);

        String expectedResponse = "Here are the tasks in your list:\n";
        expectedResponse += "1. " + dummyC + "\n";
        expectedResponse += "2. " + dummyB + "\n";
        expectedResponse += "3. " + dummyA + "\n";

        ListCommand listCommand = new ListCommand("createdAt");
        CommandResult result = listCommand.execute(taskManagerStub);

        assertEquals(expectedResponse, result.getFeedback());
    }

    @Test
    public void listTasksByUpdatedTime() throws Exception {
        List<Task> dummyTasks = new ArrayList<>();

        Task dummyA = new ToDoTask("1st created, 2nd updated");
        dummyTasks.add(dummyA);

        Task dummyB = new ToDoTask("2nd created, 1st updated");
        dummyTasks.add(dummyB);

        Task dummyC = new ToDoTask("3rd created, 3rd updated");
        dummyTasks.add(dummyC);

        Thread.sleep(1000);
        dummyA.markAsDone();
        Thread.sleep(1000);
        dummyC.markAsDone();

        TaskManagerStub taskManagerStub = new TaskManagerStub("data/test.txt");
        taskManagerStub.setTasks(dummyTasks);

        String expectedResponse = "Here are the tasks in your list:\n";
        expectedResponse += "1. " + dummyC + "\n";
        expectedResponse += "2. " + dummyA + "\n";
        expectedResponse += "3. " + dummyB + "\n";

        ListCommand listCommand = new ListCommand("updatedAt");
        CommandResult result = listCommand.execute(taskManagerStub);

        assertEquals(expectedResponse, result.getFeedback());
    }
}
