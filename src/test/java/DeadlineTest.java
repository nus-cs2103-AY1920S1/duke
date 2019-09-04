
import org.junit.jupiter.api.Test;
import duke.task.Deadline;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    String time = "19/09/2019 2359";
    Deadline deadline;

    public DeadlineTest() throws ParseException {
        deadline = new Deadline ("Submit Assignment", time);
    }

    @Test
    public void testToString() {
        assertEquals("[D]" + "[" + "\u2718" + "] " + deadline.getDescription() + "(by: " + deadline.getTime() + ")", deadline.toString());
    }

    @Test
    public void testGetTime() {
        assertEquals(time, deadline.getTime());
    }

}