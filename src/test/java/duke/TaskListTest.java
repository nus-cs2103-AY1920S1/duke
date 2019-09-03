import duke.TaskList;
import duke.task.Todo;
import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("description"));
        assertEquals(1, taskList.size());
        taskList.remove(1);
    }
}