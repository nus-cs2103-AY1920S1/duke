import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.tasks.Event;

public class EventTest {
    @Test
    public void dummyTest() {
        assertEquals("[E][✘] Basketball Competition (at: 03/11/2018 1700)",
                new Event("Basketball Competition", "03/11/2018 1700").toString());
    }
}
