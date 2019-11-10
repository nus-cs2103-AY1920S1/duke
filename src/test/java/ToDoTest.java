import org.junit.jupiter.api.Test;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    void testStringConversion() {
        assertEquals("[T][0] Test", (new ToDo("Test")).toString());
    }
}