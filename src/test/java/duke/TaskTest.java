package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Task t = new Task("blah blah", false);

    @Test
    public void testToString() {
        assertEquals("[ ] blah blah", t.toString());
        assertNotEquals("[ ] blah", t.toString());
        assertNotEquals("[ ] blah ", t.toString());
        assertNotEquals("[ ] blah bla", t.toString());
    }

    @Test
    public void testSetDone() {
        t.setDone();
        assertEquals(1, t.isDone());
    }
}
