package tasks;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void markAsDone_todo_pass() {
        Todo todo = new Todo("A todo task.");
        todo.markAsDone();
        assertTrue(todo.getIsDone());
    }
}
