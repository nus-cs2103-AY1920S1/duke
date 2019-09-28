import duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testSetDone() {
        Task testTask = new Task("return book to library");
        assertEquals(testTask.toString(),"[✘] return book to library");
        testTask.setDone();
        assertEquals(testTask.toString(),"[✓] return book to library");
    }

}
