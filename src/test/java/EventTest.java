import org.junit.jupiter.api.Test;
import tasks.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void testDescriptionGetter() {
        assertEquals("Test | 12/12/2000 0800", new Event("Test", "12/12/2000 0800").getDescription());
    }

    @Test
    void testStringConversion() {
        assertEquals("[E][0] Test (at: 12/12/2000 0800)",
                new Event("Test", "12/12/2000 0800").toString());
    }
}