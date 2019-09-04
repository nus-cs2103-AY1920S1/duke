import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void TodoTest() {
        Task todo = new Todo("Make the todo test");
        assertTrue(todo.toString().equals("[T][X] Make the todo test"));
        assertTrue(todo.getType().equals("todo"));
    }
}
