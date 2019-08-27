package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getPeriod() {
        Event event = new Event("clean house", "2/12/2019 1800", false);
        assertEquals("2/12/2019 1800", event.getPeriod());
    }

    @Test
    void toString1() {
        Event event = new Event("clean house", "2/12/2019 1800", false);
        assertEquals("[E][X] clean house (at: 2/12/2019 1800)", event.toString());
    }
}