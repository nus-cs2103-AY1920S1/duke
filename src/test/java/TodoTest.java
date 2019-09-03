import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void dummyTest() {
        Task task = new Todo("read book");
        assertTrue(task.toString().equals("[T][X] read book"));
    }
}