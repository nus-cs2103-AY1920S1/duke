package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class DukeExceptionTest {
    @Test
    void testExceptionConstruction() {
        DukeException exception = new DukeException("Test");
        assertEquals("☹ OOPS!!! Test", exception.getMessage());
    }
}
