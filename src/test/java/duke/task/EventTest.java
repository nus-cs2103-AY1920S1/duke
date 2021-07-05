package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void getDate_eventWithDescAndDate_sameDateReturned() {
        Event event = new Event("A description", "20/12/2019 0000");
        assertEquals("20/12/2019 0000", event.getDateString());
    }

    @Test
    void toString_eventWithDescAndDate_eventStringReturned() {
        Event event = new Event("A description", "20/12/2019 0000");
        assertEquals("[E][\u2718] A description (at: December 20, 2019 at 0000)", event.toString());
    }

}