package tasks;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import utils.StringToDate;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private Event event;

    EventTest() throws ParseException, DukeException {
        event = new Event("description", new StringToDate("10-10-2019 18:00"));
    }

    @Test
    void printForStorage_taskObject_formattedTaskString() {
        assertEquals("E | 0 | description | 10-10-2019 18:00", event.printForStorage());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[E][✗] description (at: 10-10-2019 18:00)", event.toString());
    }
}
