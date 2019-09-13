import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.DukeUi;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void constructTaskListTest() {
        TaskList test = new TaskList(new ArrayList<Task>());
        Task testTask = new Task("buy bread");
        test.add(testTask);
        assertEquals(test.size(), 1);
        assertEquals(test.get(0), testTask);
        test.delete(1);
        assertEquals(test.size(), 0);
    }
}
