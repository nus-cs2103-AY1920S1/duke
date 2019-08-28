import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void deleteTask_wrongIndex_exceptionThrown() {
        try {
            new TaskList().deleteTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("No task with index number 2 on your tasklist!", e.getMessage());
        }
    }
}