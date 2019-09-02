import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void formatToWrite() {
        assertEquals("T | 0 | say hi", new Todo("say hi").formatToWrite());
    }
}
