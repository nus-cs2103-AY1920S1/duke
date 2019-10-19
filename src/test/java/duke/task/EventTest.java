package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EventTest {

    @Test
    void constructorTest_invalidTimeFormat_exceptionThrown() {
        try {
            Event event = new Event("Test", "99/99/9999 9999 9999");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid time format.", e.getMessage());
        }
    }

    @Test
    void constructorTest_startAfterEndDate_exceptionThrown() {
        try {
            Event event = new Event("Test", "02/01/2019 0700 01/01/2019 1500");
            fail();
        } catch (DukeException e) {
            assertEquals("Start date cannot be after end date", e.getMessage());
        }
    }

    @Test
    void constructorTest_startAfterEndTime_exceptionThrown() {
        try {
            Event event = new Event("Test", "01/01/2019 1500 0700");
            fail();
        } catch (DukeException e) {
            assertEquals("Start time cannot be after end time", e.getMessage());
        }
    }

    @Test
    void stringConversion_onSameDate() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 1500");
        assertEquals("[E][✘] Test (at: 1 January 2019, 07:00AM - 03:00PM)",
                event.toString());
    }

    @Test
    void stringConversion_onDifferentDates() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 02/01/2019 1500");
        assertEquals("[E][✘] Test (at: 1 January 2019, 07:00AM - 2 January 2019, 03:00PM)",
                event.toString());
    }

    @Test
    void encode() throws DukeException {
        Event event = new Event("Test", "01/01/2019 0700 1500");
        assertEquals("E | 0 | Test | 01/01/2019 0700 01/01/2019 1500", event.encode());
    }
}
