package tasks;

import customexceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the test for a deadline object.
 */
public class DeadlineTest {
    /**
     * Tests the toString() method of a deadline object.
     * @throws DukeException if the date time has been input in the wrong format.
     */
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("D|-|test|20/10/2019 2100", new Deadline("test","20/10/2019 2100").toString());
    }
}
