package duke.test;

import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest{

    @Test
    public void testGetTaskStatus() throws DukeException {
            Todo test = new Todo(" TEST");
            assertEquals("[T] [✗] TEST", test.getTaskStatus());
    }


}
