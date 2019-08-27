package duke.component;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void getTask_emptyList_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            new TaskList(new LinkedList<>()).getTask(1);
        });
    }
}
