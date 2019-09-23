package duke.command;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void constructor_validArguments_success() throws Exception {
        Command command = new DoneCommand("done 2");
    }

    @Test
    public void constructor_invalidArgumentsFormat_exceptionThrown() {
        try {
            Command command = new DoneCommand("done asd");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The argument should be a number.", e.getMessage());
        }
        try {
            Command command = new DoneCommand("done");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Insufficient arguments for this command.", e.getMessage());
        }
        try {
            Command command = new DoneCommand("done 1 1");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There are too many arguments for this command.", e.getMessage());
        }
    }

    @Test
    public void isExit_success() throws Exception {
        assertEquals((new DoneCommand("done 2")).isExit(), false);
    }
}
