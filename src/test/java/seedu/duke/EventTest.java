package seedu.duke;

import seedu.duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EventTest is used to test the methods in Event Class
 */
public class EventTest {

    /**
     * Test how the attributes are initialized when a String description is supplied.
     */
    @Test
    void initializeAttributes_stringDescription_correctAttributes() {
        assertEquals("[E][✘] description (at: location)", new Event("description", "location").toString());
    }

    /**
     * Tests if the parsed saved String matches the method output.
     */
    @Test
    void saveStringOutput_stringDescription_correctSavedString() {
        assertEquals("E | 0 | description | location", new Event("description", "location").toSaveString());
    }
}