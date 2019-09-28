import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTaskTest {
    @Test
    public void toString_completedTask_tickText() {
        assertEquals("[E][✗] lecture (At: 12 December 2019, 4PM)",
                new EventsTask("lecture /at 12/12/2019 16:00").toString());
    }

    @Test
    public void toString_invalidInput_exception() throws Exception {
        try {
            assertEquals(0, new EventsTask("hello world"));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! The description /at of a event task cannot be empty.",e.toString());
        }
    }

    @Test
    public void toString_invalidDate_exception() throws Exception {
        try {
            assertEquals(0, new EventsTask("hello world /at :)"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, Dates should be in format 'dd/mm/yyyy hh:mm'",e.toString());
        }
    }
}