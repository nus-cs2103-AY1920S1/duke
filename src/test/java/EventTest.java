import duke.parser.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("Meeting", "19/9/2019");

    @Test
    public void EventGetDescriptionTest() {
        assertEquals(event.getDescription(), "Meeting");

    }

    @Test
    public void EventGetInformationTest() {
        assertEquals(event.getInformation(), "(at: 19/9/2019)");
    }

    @Test
    public void EventGetStatusBitTest() {
        assertEquals(event.getStatusBit(), 0);
    }
}
