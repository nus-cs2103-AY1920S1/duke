package duke.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.exceptions.DukeException;
import duke.utils.TaskList;
import duke.tasks.Task;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testTaskListSize() {
        try {
            TaskList allTasks = new TaskList(new ArrayList<Task>());
            allTasks.addToDo("Test ToDo");
            allTasks.addEvent("Test Event", "26/02/1997 09:00", "26/02/1997 18:00");
            allTasks.addDeadline("Test Deadline", "26/02/1997 09:00");
            assertEquals(3, allTasks.size());
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
