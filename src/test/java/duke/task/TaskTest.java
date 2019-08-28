package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void completeTask_success() {
        Task t = new Todo("Todo1");
        t.setTaskCompleted();
        assertEquals(t.getDoneStatus(), true);

        t = new Event("Event1", "1/1/1900", "0000");
        t.setTaskCompleted();
        assertEquals(t.getDoneStatus(), true);

        t = new Deadline("Deadline1", "1/1/1900", "0000");
        t.setTaskCompleted();
        assertEquals(t.getDoneStatus(), true);
    }

}
