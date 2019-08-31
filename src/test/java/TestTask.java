import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the JUnit tests for the Task class.
 */
public class TestTask {
    @Test
    /**
     * Tests the toString method.
     */
    public void testStringConversion(){
        assertEquals("[" + "\u2718" + "] test",new Task("test").toString());
    }

    /**
     * Tests the markAsDone method.
     */
    @Test
    public void testMarkAsDone(){
        Task t = new Task("test");
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
