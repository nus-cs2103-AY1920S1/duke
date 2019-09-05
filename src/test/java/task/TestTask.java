package task;

import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("return a book");
    }

    @Test
    public void markAsDone() {
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void toString_all() {
        assertEquals("[✘] return a book", task.toString());
        task.markAsDone();
        assertEquals("[✓] return a book", task.toString());
    }

    @Test
    public void stringify() {
        assertEquals("0 | return a book", task.stringify());
        task.markAsDone();
        assertEquals("1 | return a book", task.stringify());
    }
}
