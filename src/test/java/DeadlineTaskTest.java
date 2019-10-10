import static org.junit.jupiter.api.Assertions.assertEquals;

import com.leeyiyuan.task.DeadlineTask;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void testOperations() {
        DeadlineTask task = new DeadlineTask();

        task.setTitle("random title");
        task.setBy(LocalDateTime.of(2019, 8, 25, 12, 0));
        assertEquals(task.toString(), "[D][✗] random title (by: 25/8/2019 1200)");

        task.setIsDone(true);
        assertEquals(task.toString(), "[D][✓] random title (by: 25/8/2019 1200)");

        task.setTitle("another title");
        assertEquals(task.toString(), "[D][✓] another title (by: 25/8/2019 1200)");

        task.setBy(LocalDateTime.of(2000, 1, 2, 13, 45));
        assertEquals(task.toString(), "[D][✓] another title (by: 2/1/2000 1345)");
    }
}
