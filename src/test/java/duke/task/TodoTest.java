package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Todo Function</h1>
 * The TodoTest tests the Todo class function
 * prints error when test is not correct
 *
 *  @author  TSAI, HSIAO-HAN
 *  @version 0.1
 *  @since   2019-9-1
 */
public class TodoTest {
    /**
     * This is the method which checks the get icon method.
     */
    @Test
    public void testIcon() {
        assertEquals("\u2718", new Todo("read books").getStatusIcon());
    }

    /**
     * This is the method which checks the markAsDone method.
     */
    @Test
    public void markAsDoneFunction_noInput_true() {
        Task todo = new Todo("read books");
        todo.markAsDone();
        assertEquals(true, todo.isDone);
    }

    /**
     * This is the method which checks the toString method.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] read books", new Todo("read books").toString());
    }

    /**
     * This is the method which checks the toFile method.
     */
    @Test
    public void testStringToFileConversion() {
        assertEquals("T | 0 | read books", new Todo("read books").toFile());
    }

}
