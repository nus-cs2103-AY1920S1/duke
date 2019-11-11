package duke.task;

import duke.ui.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void saveTask_Test_success() throws DukeException {
        assertEquals("T | 0 | eat dinner",
                new Todo(false, "eat dinner").saveTask());
        assertEquals("T | 1 | play tennis",
                new Todo(true, "play tennis").saveTask());
    }

    @Test
    public void toString_Test_success() throws DukeException {
        assertEquals(" [T][✘] report submission",
                (new Todo(false, "report submission")).toString());
    }
}
