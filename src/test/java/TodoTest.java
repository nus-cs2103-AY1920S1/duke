import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public static void todoTest() {
        assertEquals("T -- \u2718 -- eat",
                new Todo("eat").toString());
    }
}
