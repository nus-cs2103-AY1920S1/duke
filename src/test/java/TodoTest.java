import task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printTodo() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][✘] borrow book", todo.toString());
    }

    @Test
    public void printTodoToFile() {
        Todo todo = new Todo("borrow book");
        assertEquals("T | 0 | borrow book", todo.toStringFile());
    }
}
