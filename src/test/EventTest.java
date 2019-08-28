import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void toTextFile() {
        Event newEvent = new Event("project meeting", "7th May");
        assertEquals("E | 0 | project meeting | 7th May", newEvent.toTextFile());
    }

    @Test
    void testToString() {
        Event newEvent = new Event("project meeting", "7th May");
        assertEquals("[E]" + "[\u2718]" + " " + "project meeting (at: 7th May)", newEvent.toString());
    }

    @Test
    void getStatusIcon() {
        Event newEvent = new Event("project meeting", "7th May");
        assertEquals("[\u2718]", newEvent.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Event newEvent = new Event("project meeting", "7th May");
        newEvent.markAsDone();
        assertEquals("[\u2713]", newEvent.getStatusIcon());
    }

}