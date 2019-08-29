import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public static void eventTest() throws ParseException {
        assertEquals("E -- \u2718 -- eat -- 01/09/2019 12:00",
                new Event("eat", "01/09/2019 12:00").toString());
    }
}
