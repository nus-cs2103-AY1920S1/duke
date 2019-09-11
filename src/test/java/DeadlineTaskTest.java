import duke.task.DeadlineTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][Not done] task (by: 1/1/1991 1234)",
                new DeadlineTask("task", "1/1/1991 1234").toString());
    }
}
