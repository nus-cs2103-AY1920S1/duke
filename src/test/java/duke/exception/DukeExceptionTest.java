package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DukeExceptionTest {
    DukeException test;
    @Test
    public void ExceptionTest(){
        test = new DukeException("test for DukeException");
        assertEquals("     ☹ OOPS!!! " + test.getMessage(),
                "     ☹ OOPS!!! " + "test for DukeException");
    }
}