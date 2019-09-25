package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.core.DukeException;
import seedu.duke.task.Todo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TodoTest is used to test the methods in Todo Class.
 */
public class TodoTest {

    /**
     * Test how the attributes are initialized when a String description is supplied.
     */
    @Test
    void initializeAttributes_stringDescription_correctAttributes() throws DukeException {
        assertEquals("[T][✘] description", new Todo("description").toString());
    }

    /**
     * Tests if the parsed saved String matches the method output.
     */
    @Test
    void saveStringOutput_stringDescription_correctSavedString() {
        String expected = "";
        expected = "T | 0 | desc |  dummyExtraDescriptionForTodo | 2019-09-14T23:30:31.894880900 "
                + "| 2019-09-14T23:30:31.894880900";
        LocalDateTime fakeTime = LocalDateTime.parse("2019-09-14T23:30:31.894880900");
        Todo newTodo = new Todo("desc", false, fakeTime, fakeTime);
        assertEquals(expected, newTodo.toSaveString());
    }

}
