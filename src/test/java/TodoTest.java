import cs2103t.duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void createCorrectTodo() {
        assertEquals("[T][\u2717] a todo task", Todo.create("a todo task").toString());
    }

    @Test
    public void createTodo_throwsException() {
        try {
            assertEquals("", Todo.create("").toString());
            fail();
        } catch (Exception e) {
            assertEquals("a todo", e.getMessage());
        }
    }

    @Test
    public void getDescription() {
        assertEquals("some todo", Todo.create("some todo").getDescription());
    }
}
