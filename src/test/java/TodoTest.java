import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void DummyTest() {
        Todo todo = new Todo("say hi");
        assertEquals(todo.toString(), "[T][✘] say hi");
    }
}
