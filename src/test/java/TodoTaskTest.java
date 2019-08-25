import static org.junit.jupiter.api.Assertions.assertEquals;

import com.leeyiyuan.task.TodoTask;
import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void testOperations() {
        TodoTask task = new TodoTask();

        task.setTitle("random title");
        assertEquals(task.toString(), "[T][✗] random title");

        task.setIsDone(true);
        assertEquals(task.toString(), "[T][✓] random title");

        task.setTitle("another title");
        assertEquals(task.toString(), "[T][✓] another title");
    }
}
