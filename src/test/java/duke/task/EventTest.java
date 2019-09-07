package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {
    @Test
    void eventTestWithString() {
        Event test = new Event("event", "eventTime");
        assertEquals("[E][X] event (at: eventTime)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][O] event (at: eventTime)", test.toString(), "markAsDone() method works");
    }

    @Test
    void eventTestWithDate() {
        Event test = new Event("event", new Date(0));
        assertTrue(test.toString().contains("[E][X] event"), "toString() method works");

        test.markAsDone();
        assertTrue(test.toString().contains("[E][O] event"), "toString() method works");
    }
}
