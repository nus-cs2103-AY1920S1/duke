package duke.task;

import duke.exception.IllegalDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testStringConversionForFile() throws IllegalDescriptionException {
        Task task = new Task("test");
        assertEquals("T | 0 | test", task.toStringForFile());
    }

    @Test
    public void testStringConversion() throws IllegalDescriptionException {
        Task task = new Task("test");
        assertEquals("[\u2718] test", task.toString());
    }

    @Test
    public void statusIcon_notDone_cross() throws IllegalDescriptionException {
        Task task = new Task("test");
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    public void statusIcon_done_tick() throws IllegalDescriptionException {
        Task task = new Task("test");
        task.setDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void constructor_emptyDescription_exceptionThrown (){
        try {
            Task task = new Task("");
            fail();
        } catch (IllegalDescriptionException e) {
            assertEquals("The description of a task cannot be empty.", e.getMessage());
        }
    }
}
