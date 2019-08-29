package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void stringConversion_onSameDate() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 1500");
        assertEquals("[E][\u2718] Test (at: 1 January 2019, 07:00AM - 03:00PM)",
                event.toString());
    }

    @Test
    void stringConversion_onDifferentDates() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 02/01/2019 1500");
        assertEquals("[E][\u2718] Test (at: 1 January 2019, 07:00AM - 2 January 2019, 03:00PM)",
                event.toString());
    }

    @Test
    void serialize() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 1500");
        assertEquals("E | 0 | Test | 01/01/2019 0700 01/01/2019 1500", event.serialize());
    }
}
