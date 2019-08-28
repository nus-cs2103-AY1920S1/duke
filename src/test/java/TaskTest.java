import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testingGetLabel() {
        assertEquals("T", new ToDo("testing").getLabel());
        assertEquals("D", new DeadLine("testing", "tomorrow").getLabel());
        assertEquals("E", new Event("testing", "tomorrow").getLabel());
    }

    @Test
    void testingGetTime() {
        assertEquals("", new ToDo("testing").getTime());
        assertEquals("tomorrow", new DeadLine("testing", "tomorrow").getTime());
        assertEquals("tomorrow", new Event("testing", "tomorrow").getTime());
    }

    @Test
    void getStatus() {
        DeadLine d = new DeadLine("testing", "tomorrow");
        d.mark();
        assertEquals(0, new DeadLine("testing", "tomorrow").getStatus());
        assertEquals(1, d.getStatus());
    }

    @Test
    void getDescription() {
        assertEquals("testing", new ToDo("testing").getDescription());
        assertEquals("testing", new DeadLine("testing", "tomorrow").getDescription());
    }

    @Test
    void mark() {
        DeadLine d = new DeadLine("testing", "tomorrow");
        assertEquals(0, d.getStatus());
        d.mark();
        assertEquals(1, d.getStatus());
    }

    @Test
    void getStatusIcon() {
        DeadLine d = new DeadLine("testing", "tomorrow");
        d.mark();
        assertEquals("[\u2718] ", new DeadLine("testing", "tomorrow").getStatusIcon());
        assertEquals("[\u2713] ", d.getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[T][\u2718] testing", new ToDo("testing").toString());
        assertEquals("[D][\u2718] testing (by: tomorrow)", new DeadLine("testing", "tomorrow").toString());
        assertEquals("[E][\u2718] testing (at: tomorrow)", new Event("testing", "tomorrow").toString());
    }
}