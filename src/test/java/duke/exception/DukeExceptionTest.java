package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DukeExceptionTest {
    DukeException test;

    @Test
    public void exceptionTest() {
        test = new DukeException("test for DukeException");
        assertEquals("     ☹ OOPS!!! " + test.getMessage(),
                "     ☹ OOPS!!! " + "test for DukeException");
    }
}