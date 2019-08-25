import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void getTaskTest() {
        TaskList t = new TaskList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            t.getTask(1);
        });
    }

    @Test
    public void completeTaskTest() {
        TaskList t = new TaskList();
        Assertions.assertThrows(DukeException.class, () -> {
            t.completeTask(1);
        });
    }

    @Test
    public void deleteTaskTest() {
        TaskList t = new TaskList();
        Assertions.assertThrows(DukeException.class, () -> {
            t.deleteTask(1);
        });
    }




}
