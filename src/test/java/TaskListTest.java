import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

public class TaskListTest {
    @Test
    public void listTest() throws DukeException {
        ArrayList<Task> testTaskList = new ArrayList<Task>();
        testTaskList.add(new Task("T", "Task 0"));
        testTaskList.add(new Task("T", "Test Task 1"));
        TaskList test = new TaskList(testTaskList);
        assertEquals("Here are the tasks in your list:\n1.[T][x] Test Task 1 ", test.list());
    }
}
