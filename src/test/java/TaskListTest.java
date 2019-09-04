import org.junit.jupiter.api.Test;

import duke.task.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAdd() throws DukeException {
        TaskList tasklist = new TaskList();
        tasklist.add(new Todo("Test"));
        assertEquals(new Todo("Test").getDescription(), tasklist.get(0).getDescription());
    }
}
