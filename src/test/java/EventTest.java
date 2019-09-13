import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void constructEventTest() {
        Event test = new Event("meeting", "Sunday, 2-4pm");
        assertEquals(test.getPeriod(), "Sunday, 2-4pm");
        assertEquals(test.toString(), "[E][" + "\u2718" + "] meeting (at: Sunday, 2-4pm)");
    }
}
