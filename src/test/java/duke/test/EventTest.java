package duke.test;

import duke.exception.DukeException;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the Event class.
 */
public class EventTest {

    /**
     * Tests that the event date is formatted correctly.
     *
     * @throws DukeException if there is an issue during the test
     */
    @Test
    public void testGetEventDate() throws DukeException {
        Event test = new Event("TEST", "03/12/1997 0800");
        assertEquals("3rd of December 1997, 8.00AM",test.getEventDate());


    }

}
