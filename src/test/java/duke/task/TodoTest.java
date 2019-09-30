package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    private static final String TODO_STUB = "write code";

    @Test
    public void testStringConversion() {
        assertEquals(TODO_STUB, new Todo(TODO_STUB).toString());
    }

    @Test
    public void newTodo_isDone_false() {
        Todo todo = new Todo(TODO_STUB);
        assertFalse(todo.isDone());
    }

    @Test
    public void markAsDone_regularTodo_success() {
        Todo todo = new Todo("play");
        assertFalse(todo.isDone());
        todo.markAsDone();
        assertTrue(todo.isDone());
    }
}
