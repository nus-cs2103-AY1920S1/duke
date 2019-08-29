package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testSize() {
        assertEquals(0, new TaskList().size());
    }

    @Test
    public void testAdd() {
        assertEquals(true, new TaskList().add(new ToDo("event")));
    }
}
