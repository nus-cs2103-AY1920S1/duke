import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("Cook lunch ", " 11am - 12pm Today");
        assertEquals("[E][ ] Cook lunch (at: 11am - 12pm Today)", event.toString());
    }
}