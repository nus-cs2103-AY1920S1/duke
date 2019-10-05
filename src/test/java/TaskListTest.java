import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.TaskDoesNotExistException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static final String FILE_PATH = "./dukeTest.txt";
    private Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;

    @BeforeEach
    public void setUpTaskListObject() {
        tasks = new TaskList();
        try {
            tasks.addTask(new Todo("borrow books", false, "#"));
            tasks.addTask(new Event("project meeting", "28/08/2019 1800 - 28/08/2019 2030", false, "#"));
        } catch (Exception e) {
            System.out.println("Adding tasks, setup step should be successful. " + e.getMessage());
            fail();
        }
    }

    @Test
    public void deleteTask_taskExists_success() {
        List<Task> taskList = tasks.getList();
        assertEquals(2, taskList.size());

        try {
            tasks.deleteTask(1);
        } catch (TaskDoesNotExistException e) {
            System.out.println("Task exists, this exception should not be thrown " + e.getMessage());
            fail();
        }

        assertEquals(1, taskList.size());
        assertEquals(taskList.get(0).getTaskName(), "project meeting" );
    }

    @Test
    public void deleteTask_taskDoesNotExist_exceptionThrown() {
        List<Task> taskList = tasks.getList();
        assertEquals(2, taskList.size());

        try {
            tasks.deleteTask(10);
            fail();
        } catch (TaskDoesNotExistException e) {
            assertEquals("Task not found", e.getMessage());
        }

    }
}
