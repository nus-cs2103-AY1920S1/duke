package duke.initials;

import org.junit.jupiter.api.Test;
import duke.*;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testGetData() {
        Event event = new Event("read book", "18th of February 2019, 6:00pm");
        assertEquals("E|0| read book| 18th of February 2019, 6:00pm", event.getData());
    }
}