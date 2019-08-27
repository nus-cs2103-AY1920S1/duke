import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructDeadlineTest() {
        Deadline test = new Deadline("return book", "15/8/19, 0845");
        assertEquals(test.getTime(), "15/8/19, 0845");
        assertEquals(test.toString(), "[D][" + "\u2718" + "] return book (by: 15/8/19, 0845)");
    }

}
