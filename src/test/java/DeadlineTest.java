import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testTaskType() {
        assertEquals('D', new Deadline("cs2103 ip", "30/09/2019, 2359").getTaskType());
    }

    @Test
    public void testNewTaskIsUndone() {
        assertEquals("\u2718", new Deadline("cs2103 ip", "30/09/2019, 2359").getStatusIcon());
    }
}
