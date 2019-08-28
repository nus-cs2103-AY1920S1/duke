package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] write code", new Todo("write code").toString());
    }

    @Test
    public void markAsDone_regularTask_success() {
        Todo todo = new Todo("play");
        todo.markAsDone();
        assertTrue(todo.isDone());
    }
}
