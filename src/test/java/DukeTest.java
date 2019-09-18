import duke.task.Deadline;
import duke.task.Todo;

import duke.command.Storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    @Test
    public void testDeadline() {
        assertEquals("D - 0 - Assignment - 30/8/2019 2359",
                new Deadline("Assignment", "30/8/2019 2359").toString());
    }

    @Test
    public void testTodo() {
        assertEquals("T - 0 - Reading", new Todo("Reading").toString());
    }
}
