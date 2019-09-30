import duke.TaskList;
import duke.Storage;
import duke.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void markDone_exceptionThrown() {
        TaskList tl = new TaskList(new Storage());
        tl.add(new Todo("print lecture notes"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            int[] temp = { 0 };
            tl.delete(temp);
        });
    }

    @Test
    public void delete_exceptionThrown() {
        TaskList tl = new TaskList(new Storage());
        tl.add(new Todo("print lecture notes"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            int[] temp = { 0 };
            tl.delete(temp);
        });
    }
}
