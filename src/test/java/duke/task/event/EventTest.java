package duke.task.event;

import duke.task.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void getAt() {
        Event event = new Event("project meeting", "Friday, 4th of July 2019, 8:00am");
        String expected = "Friday, 4th of July 2019, 8:00am";
        Assertions.assertEquals(expected, event.getAt());
    }

    @Test
    void testToString() {
        Event event = new Event("project meeting", "Friday, 4th of July 2019, 8:00am");
        String expected = "[E][✘] project meeting (at: Friday, 4th of July 2019, 8:00am)";
        Assertions.assertEquals(expected, event.toString());
    }
}