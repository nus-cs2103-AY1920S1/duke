import static org.junit.jupiter.api.Assertions.assertEquals;

import com.leeyiyuan.task.EventTask;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void testOperations() {
        EventTask task = new EventTask();

        task.setTitle("random title");
        task.setAt(LocalDateTime.of(2019, 8, 25, 12, 0));
        assertEquals(task.toString(), "[E][✗] random title (at: 25/8/2019 1200)");

        task.setIsDone(true);
        assertEquals(task.toString(), "[E][✓] random title (at: 25/8/2019 1200)");

        task.setTitle("another title");
        assertEquals(task.toString(), "[E][✓] another title (at: 25/8/2019 1200)");

        task.setAt(LocalDateTime.of(2000, 1, 2, 13, 45));
        assertEquals(task.toString(), "[E][✓] another title (at: 2/1/2000 1345)");
    }
}
