import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public static void deadlineTest() throws ParseException {
        assertEquals("D -- \u2718 -- eat -- 01/09/2019 12:00",
                new Deadline("eat", "01/09/2019 12:00").toString());
    }
}
