
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.Todo;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    Task todo = new Todo ("Return book");

    @Test
    public void testGetTypeOfTask() {
        assertEquals("T", this.todo.getTypeOfTask());
    }

    @Test
    public void testIsDone() {
        assertEquals(false, this.todo.isDone());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("\u2718", todo.getStatusIcon());
    }
}
