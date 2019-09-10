import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("Meeting", "19/9/2019");

    @Test
    public void eventGetDescriptionTest() {
        assertEquals("Meeting", event.getDescription());

    }

    @Test
    public void eventGetInformationTest() {
        assertEquals("(at: 19/9/2019)", event.getInformation());
    }

    @Test
    public void eventGetStatusBitTest() {
        assertEquals(0, event.getStatusBit());
    }
}
