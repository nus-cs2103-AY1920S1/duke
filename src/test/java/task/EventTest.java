package task;

import main.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() throws DukeException {
        assertEquals(new Event("Assignment", false,
                        new DateTime("25/03/2019 2359")).toString(),
                "[E][✗] Assignment (at: March 25 2019 11:59 PM)");
    }

    @Test
    public void publishTaskTest() throws DukeException {
        assertEquals("E | 0 | Assignment | March 25 2019 11:59 PM",
                new Event("Assignment", false,
                        new DateTime("25/03/2019 2359")).publishTask());
    }

}
