package duke.main;

import java.util.ArrayList;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        assertEquals(tl.getTasksSize(), 0);
    }
}
