import duke.errands.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void getStatus() {
        Todo newTodo = new Todo("test");
        assertEquals("T | " + "0" + " | " + "test", newTodo.getStatus());
    }
} 