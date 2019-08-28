package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void markAsDone_notDone_success() throws DukeException {
        Task task = new Todo("Description");
        assertEquals(task.toString(), "[T][✗] Description");

        task.markAsDone();
        assertEquals(task.toString(), "[T][✓] Description");
    }

    @Test
    public void markAsDone_alreadyDone_ExceptionThrown() {
        try {
            Task task = new Todo("Description");
            task.markAsDone();
            task.markAsDone();
            fail();
        } catch (Exception e) {
            assertEquals("This task was marked as done before.", e.getMessage());
        }
    }
}
