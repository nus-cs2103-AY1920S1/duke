import org.junit.jupiter.api.Test;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo t = new Todo("read book");

    @Test
    public void testStorageString() throws Throwable {
        String res = t.storageString();
        assertEquals("T/0/read book", res);
    }
}
