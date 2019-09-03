import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Task check = new Event("project meeting", "04/06/2019 1030");

    @Test
    void testToString() {
        String expected = "[E][" + "\u2718" + "] project meeting (at: Tue Jun 04 10:30:00 SGT 2019)";
        assertEquals(expected, check.toString());
    }

    @Test
    void toSave() {
        String expected = "E | 0 | project meeting | 04/06/2019 1030";
        assertEquals(expected, check.toSave());
    }
}