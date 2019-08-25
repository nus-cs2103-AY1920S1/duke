package duke.calendar;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TimeTest {

    @Test
    public void processedTime_success() throws DukeException {
        Time time = new Time("0000");
        assertEquals("12am", time.toString());
    }

    @Test
    public void processedDate_wrongHour_exceptionThrown() throws DukeException {
        try {
            Time time = new Time("2450");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please input a valid time.", e.getMessage());
        }
    }

    @Test
    public void processedDate_wrongMinute_exceptionThrown() throws DukeException {
        try {
            Time time = new Time("2361");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please input a valid time.", e.getMessage());
        }
    }

    @Test
    public void processedDate_wrongFormat_exceptionThrown() throws DukeException {
        try {
            Time time = new Time("17092");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please input a valid time e.g. 1800.", e.getMessage());
        }
    }
}

