import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTaskType() {
        assertEquals('T', new ToDo("cs2103 ip").getTaskType());
    }

    @Test
    public void testNewTaskIsUndone() {
        assertEquals("\u2718", new ToDo("cs2103 ip").getStatusIcon());
    }
}
