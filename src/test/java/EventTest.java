import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("Meeting", "19/9/2019");

    @Test
    public void EventGetDescriptionTest() {
        assertEquals("Meeting", event.getDescription());

    }

    @Test
    public void EventGetInformationTest() {
        assertEquals("(at: 19/9/2019)", event.getInformation());
    }

    @Test
    public void EventGetStatusBitTest() {
        assertEquals(0, event.getStatusBit());
    }
}
