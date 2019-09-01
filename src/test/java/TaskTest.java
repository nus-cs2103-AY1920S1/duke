import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.task.Task;

public class TaskTest {
    @Test
    public void testToString() {
        assertEquals("[N] test", new Task("test").toString());
    }

    @Test
    public void testToFile() {
        assertEquals("T|N|test", new Task("test").toFile());
    }
}
