package tasks;

import customexceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the event object.
 */
public class EventTest {
    /**
     * Tests the toString() method of the event object.
     * @throws DukeException if the date time has been input in the wrong format.
     */
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("E|-|test|20/10/2019 2100", new Event("test","20/10/2019 2100").toString());
    }
}
