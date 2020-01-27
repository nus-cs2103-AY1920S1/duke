import TaskList.Task;
import TaskList.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getStatusNumberTest() {
        Task newTask = new ToDos("test");
        assertEquals(0, newTask.getStatusNumber());
    }
}
