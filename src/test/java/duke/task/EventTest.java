package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getDate() {
        Event event = new Event("A description", "20/12/2019 0000");
        assertEquals("20/12/2019 0000", event.getDate());
    }

    @Test
    void testToString() {
        Event event = new Event("A description", "20/12/2019 0000");
        assertEquals("[E][✘] A description (at: December 20, 2019 at 0000)", event.toString());
    }

}