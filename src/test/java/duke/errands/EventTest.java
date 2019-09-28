import duke.errands.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void newEventStatus() {
        Event newEvent = new Event("test", "hall");
        assertEquals("\u2718", newEvent.getStatusIcon());
    }

    @Test
    void testMarkAsDone() {
        Event newEvent = new Event("test2", "mpsh");
        newEvent.markAsDone();
        assertEquals("\u2713", newEvent.getStatusIcon());
    }
} 