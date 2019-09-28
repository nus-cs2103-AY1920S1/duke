import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testTaskType() {
        assertEquals('E', new Event("cs2103 ip", "30/09/2019, 2359").getTaskType());
    }

    @Test
    public void testNewTaskIsUndone() {
        assertEquals("\u2718", new Event("cs2103 ip", "30/09/2019, 2359").getStatusIcon());
    }
}
