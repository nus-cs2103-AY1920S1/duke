import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testingGetLabel() {
        assertEquals("T", new ToDo("testing").getLabel());
        assertEquals("D", new Deadline("testing", "tomorrow").getLabel());
        assertEquals("E", new Event("testing", "tomorrow").getLabel());
    }

    @Test
    void testingGetTime() {
        assertEquals("", new ToDo("testing").getTime());
        assertEquals("tomorrow", new Deadline("testing", "tomorrow").getTime());
        assertEquals("tomorrow", new Event("testing", "tomorrow").getTime());
    }

    @Test
    void getStatus() {
        Deadline d = new Deadline("testing", "tomorrow");
        d.mark();
        assertEquals(0, new Deadline("testing", "tomorrow").getStatus());
        assertEquals(1, d.getStatus());
    }

    @Test
    void getDescription() {
        assertEquals("testing", new ToDo("testing").getDescription());
        assertEquals("testing", new Deadline("testing", "tomorrow").getDescription());
    }

    @Test
    void mark() {
        Deadline d = new Deadline("testing", "tomorrow");
        assertEquals(0, d.getStatus());
        d.mark();
        assertEquals(1, d.getStatus());
    }

    @Test
    void getStatusIcon() {
        Deadline d = new Deadline("testing", "tomorrow");
        d.mark();
        assertEquals("[\u2718] ", new Deadline("testing", "tomorrow").getStatusIcon());
        assertEquals("[\u2713] ", d.getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[T][\u2718] testing", new ToDo("testing").toString());
        assertEquals("[D][\u2718] testing (by: tomorrow)", new Deadline("testing", "tomorrow").toString());
        assertEquals("[E][\u2718] testing (at: tomorrow)", new Event("testing", "tomorrow").toString());
    }
}