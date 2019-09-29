import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void eventTestOutput() {
        assertEquals("[E][x] CS2103 project meet(at: 23rd of April 2019, 3:00 pm)",
                new Events("CS2103 project meet", "at 23/04/2019 1500").toString());
    }
}
