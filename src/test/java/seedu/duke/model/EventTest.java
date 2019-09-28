package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dto.Event;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void event() throws ParseException {
        Event event = new Event("project meeting", "18/09/19 12:00");
        assertEquals("E", event.getType());
    }
}
