package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {
    @Test
    void testStringConversion() {
        assertEquals("[T][✘] write code", new Todo("write code").toString());
    }

    @Test
    void markAsDone_regularTask_success() {
        Todo todo = new Todo("play");
        todo.markAsDone();
        assertTrue(todo.isDone());
    }
}
