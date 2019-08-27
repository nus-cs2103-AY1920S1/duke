package duke.task;

import duke.exception.IllegalDescriptionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void testStringConversion() throws IllegalDescriptionException {
        assertEquals("[E][\u2718] test (at: 01 Mar 2020 12:00)",
                new Event("test", LocalDateTime.of(2020, 3, 1, 12, 0)).toString());
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown() {
        try {
            assertEquals("", new Event("", LocalDateTime.of(2020, 1, 1, 1, 1)).toString());
            fail();
        } catch (IllegalDescriptionException e) {
            assertEquals("The description of a event cannot be empty.",e.getMessage());
        }
    }
}
