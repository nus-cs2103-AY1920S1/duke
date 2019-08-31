package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.task.Todo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TodoTest is used to test the methods in Todo Class
 */
public class TodoTest {

    /**
     * Test how the attributes are initialized when a String description is supplied.
     */
    @Test
    void initializeAttributes_stringDescription_correctAttributes(){
        assertEquals("[T][✘] description", new Todo("description").toString());
    }

    /**
     * Tests if the parsed saved String matches the method output.
     */
    @Test
    void saveStringOutput_stringDescription_correctSavedString(){
        assertEquals("T | 0 | description", new Todo("description").toSaveString());
    }

}
