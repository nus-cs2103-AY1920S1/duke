import trackr.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testGetType() {
        assertEquals("todo", new Todo("name").getType());
    }

    @Test
    public void testGetTypeIcon() {
        assertEquals("[T]", new Todo("name").getTypeIcon());
    }
}
