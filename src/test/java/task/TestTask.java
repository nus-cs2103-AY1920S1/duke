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
        assertEquals(task.toString(), "[✘] return a book");
        task.markAsDone();
        assertEquals(task.toString(), "[✓] return a book");
    }

    @Test
    public void stringify() {
        assertEquals(task.stringify(), "0 | return a book");
        task.markAsDone();
        assertEquals(task.stringify(), "1 | return a book");
    }
}
