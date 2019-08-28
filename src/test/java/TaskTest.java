import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test Task class.
 */
public class TaskTest {

    /**
     * Test method to test getStatusIcon()
     */
    @Test
    public void testGetStatusIcon() {
        Task t = new Task("read book");
        String expected = "\u2718";
        assertEquals(expected, t.getStatusIcon());
    }

    /**
     * Test method to test getDescription()
     */
    @Test
    public void testGetDescription() {
        Task t = new Task("cook dinner");
        String expected = "cook dinner";
        assertEquals(expected, t.getDescription());
    }

    /**
     * Test method to test markAsDone()
     */
    @Test
    public void testMarkAsDone() {
        Task t = new Task("clean room");
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
