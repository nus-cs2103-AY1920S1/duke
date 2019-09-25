package seedu.duke;

import seedu.duke.core.DukeException;
import seedu.duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EventTest is used to test the methods in Event Class.
 */
public class EventTest {

    /**
     * Test how the attributes are initialized when a String description is supplied.
     */
    @Test
    void initializeAttributes_stringDescription_correctAttributes() throws DukeException {
        assertEquals("[E][✘] description (at: location)", new Event("description",
                "location").toString());
    }

    /**
     * Tests if the parsed saved String matches the method output.
     */
    @Test
    void saveStringOutput_stringDescription_correctSavedString() {
        String expected = "";
        expected = "E | 1 | description | location | 2019-09-14T23:30:31.894880900 | 2019-09-14T23:30:31.894880900";
        assertEquals(expected, new Event("description",
                "location", true, LocalDateTime.parse("2019-09-14T23:30:31.894880900"),
                LocalDateTime.parse("2019-09-14T23:30:31.894880900")).toSaveString());
    }
}