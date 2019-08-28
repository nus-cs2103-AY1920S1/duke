import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getStatusIcon() {
        Event newEvent = new Event("test", "tomorrow");
        assertEquals("[\u2718]", newEvent.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Event newEvent = new Event("test2", "next week");
        newEvent.markAsDone();
        assertEquals("[\u2713]", newEvent.getStatusIcon());
    }

} 