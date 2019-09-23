package duke.command;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void constructor_validArguments_success() throws Exception {
        Command command = new DeleteCommand("delete 2");
    }

    @Test
    public void constructor_invalidArgumentsFormat_exceptionThrown() {
        try {
            Command command = new DeleteCommand("delete asd");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The argument should be a number.", e.getMessage());
        }
        try {
            Command command = new DeleteCommand("delete");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Insufficient arguments for this command.", e.getMessage());
        }
        try {
            Command command = new DeleteCommand("delete 1 1");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are too many arguments for this command.", e.getMessage());
        }
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new DeleteCommand("delete 2")).isExit(), false);
    }
}
