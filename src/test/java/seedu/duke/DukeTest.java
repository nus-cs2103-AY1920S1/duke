package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void successfulLaunch() {
        try {
            Duke duke = new Duke("data/testStorage.txt");
            assertEquals(duke.getInitialMessage(), "Hello! I'm Duke\nWhat can I do for you?");
        } catch (Exception e) {
            assertTrue(false, "Failed to create duke object");
        }
    }
}
