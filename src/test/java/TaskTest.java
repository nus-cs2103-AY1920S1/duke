import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskGetDescription() {
        Task task = new Task("complete hw");
        assertEquals("complete hw", task.getDescription());
    }

}
