import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @Test
    void deleteTask_noSuchTask_exception() {
        TaskList tl = new TaskList();
        try {
            tl.delete(1, new Ui());
            fail();
        } catch (DukeException e) {
            assertEquals("    No more tasks to delete!", e.getMessage());
        }
    }
}
