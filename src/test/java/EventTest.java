import cs2103t.duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void createCorrectEvent() {
        assertEquals("[E][\u2717] an event task (at: 3-5pm)", Event.create("an event task /at 3-5pm").toString());
    }

    @Test
    public void createEvent_throwsException() {
        try {
            assertEquals("", Event.create("").toString());
            fail();
        } catch (Exception e) {
            assertEquals("an event", e.getMessage());
        }
    }

    @Test
    public void getEventDescription() {
        assertEquals("some event | today 3-5pm", Event.create("some event /at today 3-5pm").getDescription());
    }
}
