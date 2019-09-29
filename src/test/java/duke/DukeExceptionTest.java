package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeExceptionTest {

    @Test
    public void deadlineTest() {
        String errorMessage = "Should Throw error";
        try {
            DukeException.checkValidity(true, errorMessage);
        } catch (DukeException e) {
            assertEquals(errorMessage, e.toString());
        }

        int one = 2;
        int two = 1;
        try {
            DukeException.checkValidity(two < one, "two is less than one");
        } catch (DukeException e) {
            assertEquals("two is less than one", e.toString());
        }
    }
}