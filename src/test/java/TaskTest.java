import task.Task;
import org.junit.jupiter.api.Test;
import task.TodoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void nameTest() {
        TodoTask temp = new TodoTask("hello", false);
        assertEquals("hello", temp.getName());
    }
}





