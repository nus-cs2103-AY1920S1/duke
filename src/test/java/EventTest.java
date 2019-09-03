import duke.task.Event;
import duke.util.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[E][X] lecture (at: 28/08/2019 1600)",
                new Event("lecture", "28/08/2019 1600").toString());
    }

    @Test
    public void testSaveStringConversion() throws DukeException {
        assertEquals("E | 0 | lecture | 28/08/2019 1600",
                new Event("lecture", "28/08/2019 1600").getSaveString());
    }
}
