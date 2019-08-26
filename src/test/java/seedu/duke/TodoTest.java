package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion1() {
        assertEquals("[T][✗] read book", new Todo("read book").toString());
    }

    @Test
    public void testStringConversion2() {
        Todo todoTask = new Todo("read book");
        todoTask.markAsDone();
        assertEquals("[T][✓] read book", todoTask.toString());
    }

    @Test
    public void testWriteToFile1() {
        Todo todoTask = new Todo("read book");
        todoTask.markAsDone();
        assertEquals("T | 1 | read book", todoTask.writeToFile());
    }
}
