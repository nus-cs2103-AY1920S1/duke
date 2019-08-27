import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public static void deadlineTest() throws ParseException {
        assertEquals("[D][N] what (by: 2019/07/02 09:00)",
                new Deadline("what", "2019/07/02 09:00").toString());
    }
}
