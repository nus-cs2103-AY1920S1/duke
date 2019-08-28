import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Task t = new Task("blah blah");

    @Test
    public void testToString() {
        assertEquals("[\u2718] blah blah", t.toString());
        assertNotEquals("[\u2718] blah", t.toString());
        assertNotEquals("[\u2718] blah ", t.toString());
        assertNotEquals("[\u2718] blah bla", t.toString());
    }

    @Test
    public void testSetDone() {
        t.setDone();
        assertEquals(1, t.isDone());
    }
}
