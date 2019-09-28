import duke.task.EventTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][Not done] sale (at: 1/1/1991 1234)",
            new EventTask("sale", "1/1/1991 1234").toString());
    }
}
