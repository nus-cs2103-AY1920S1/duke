import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the JUnit tests for the Task class.
 *
 */
public class TestTask {

    /**
     * Tests the toString method.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[" + "0" + "] test",new Task("test").toString());
    }

    /**
     * Tests the markAsDone method.
     */
    @Test
    public void testMarkAsDone() {
        Task t = new Task("test");
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
