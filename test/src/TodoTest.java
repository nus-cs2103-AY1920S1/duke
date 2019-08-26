import duke.Tasks.Task;
import duke.Tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    Task t;

    @Test
    public void todoTaskInfoTest() {
        t = new Todo("Eat dinner");
        assertEquals("[T][\u2715] Eat dinner", t.task_info());
    }

    @Test
    public void todoRecordInfoTest() {

    }

    @Test
    public void todoFinishTest() {

    }

}
