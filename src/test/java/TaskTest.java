import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void compareToTest() {
        LocalDateTime early = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now().plusDays(2);
        Task deadline1 = new Deadline("homework", early);
        Task deadline2 = new Deadline("assignment", later);
        assertEquals(early.compareTo(later), deadline1.compareTo(deadline2));

    }
}
