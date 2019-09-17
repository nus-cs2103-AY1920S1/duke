package duke.test;

import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the Todo class.
 */
public class TodoTest {

    /**
     * Tests that getTaskStatus() returns the correct format after the constructor is called.
     *
     * @throws DukeException when there is an error during the test
     */
    @Test
    public void testGetTaskStatus() throws DukeException {
        Todo test = new Todo(" TEST");
        assertEquals("[T] [\u2718] TEST", test.getTaskStatus());
    }


}
