package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void test() {
        Task task = new Task("meeting", false);
        String testString = "[\u2718] | meeting";
        assertEquals(task.toString(), testString);
    }
}

