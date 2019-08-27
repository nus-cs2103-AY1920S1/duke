import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void constructTaskTest() {
        Task test = new Task("buy bread");
        assertEquals(test.getDescription(), "buy bread");
        assertEquals(test.getStatus(), false);
        assertEquals(test.getStatusIcon(), "\u2718");
        assertEquals(test.toString(), "[" + test.getStatusIcon() + "] buy bread");
    }
}
