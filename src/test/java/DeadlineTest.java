import cs2103t.duke.task.Deadline;
import cs2103t.duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void createCorrectDeadline() {
        assertEquals("[D][\u2717] a deadline task (by: 29/8/2019 1817)",
                Deadline.create("a deadline task /by 29/8/2019 1817").toString());
    }

    @Test
    public void createDeadline_throwsException() {
        try {
            assertEquals("", Deadline.create("").toString());
            fail();
        } catch (Exception e) {
            assertEquals("a deadline", e.getMessage());
        }
    }

    @Test
    public void getDeadlineDescription() {
        assertEquals("some deadline | 29/8/2019 1817",
                Deadline.create("some deadline /by 29/8/2019 1817").getDescription());
    }
}
