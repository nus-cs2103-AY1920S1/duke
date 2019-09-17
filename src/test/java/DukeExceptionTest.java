import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeExceptionTest {

    @Test
    void exceptionTest() {
        DukeException test = new DukeException("test for DukeException");
        assertEquals("     ☹ OOPS!!! " + test.getMessage(),
                "     ☹ OOPS!!! " + "test for DukeException");
    }
}