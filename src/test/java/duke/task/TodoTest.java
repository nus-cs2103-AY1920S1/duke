package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testIcon() {
        assertEquals("\u2718", new Todo("read books").getStatusIcon());
    }

    @Test
    public void markAsDoneFunction_noInput_true() {
        Task todo = new Todo("read books");
        todo.markAsDone();
        assertEquals(true, todo.isDone);
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] read books", new Todo("read books").toString());
    }

    @Test
    public void testStringToFileConversion() {
        assertEquals("T | 0 | read books", new Todo("read books").toFile());
    }

}
