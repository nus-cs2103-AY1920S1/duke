import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTodoTest(){
        assertEquals("[T][-] Help me", new Todo("Help me").toString());
    }

    @Test
    public void toDataStringTodoTest(){
        assertEquals("T | 0 | Help me", new Todo("Help me").toDataString());
    }
}
