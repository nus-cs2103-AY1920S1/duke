package duke.test.Task;
import duke.helper.DateTimeHelper;
import duke.task.Event;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventTypeTest_Success() throws DukeException {
        String d = "event sing song /at 2019-09-17 0900";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Event eve = new Event("sing song", ldt);
        assertEquals("[E]", eve.getType());
    }

    @Test
    public void eventGetDescription_Success() throws DukeException {
        String d = "event sing song /at 2019-09-17 0900";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Event eve = new Event("sing song", ldt);
        assertEquals("sing song|" + DateTimeHelper.formatOutput(ldt), eve.getDescription());
    }

    @Test
    public void eventToString_Success() throws DukeException {
        String d = "event sing song /at 2019-09-17 0900";
        String assertE = "[E]" + "[" + "\u2718" + "] sing song (at: 2019-09-17 0900)";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Event eve = new Event("sing song", ldt);
        assertEquals(assertE, eve.toString());
    }
}
