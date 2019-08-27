import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public static void eventTest() throws ParseException {
        assertEquals("[E][N] what (at: 2019/07/02 09:00)",
                new Event("what", "2019/07/02 09:00").toString());
    }
}