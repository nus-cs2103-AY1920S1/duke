package seedu.duke;

import seedu.duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        assertEquals("[E][✘] description (at: location)", new Event("description", "location").toString());
    }

    @Test
    void toSaveString() {
        assertEquals("E | 0 | description | location", new Event("description", "location").toSaveString());
    }
}