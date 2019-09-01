import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest() throws ParseException {
        assertEquals("[E][0] A (at: B)", new Event("A", "B").toString());
    }

    @Test
    public void getTypeTest() throws ParseException {
        assertEquals("E", new Event("A", "B").getType());
    }

    @Test
    public void getDescriptionTest() throws ParseException {
        assertEquals("A", new Event("A", "B"));
    }
}
