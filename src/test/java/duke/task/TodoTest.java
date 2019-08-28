package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TodoTest {
    @Test
    void constructorTest_emptyDescription_exceptionThrown() {
        try {
            Task task = new Todo("");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    void markAsDone_notDone_success() throws DukeException {
        Task task = new Todo("Description");
        assertEquals(task.toString(), "[T][✘] Description");

        task.markAsDone();
        assertEquals(task.toString(), "[T][✓] Description");
    }

    @Test
    void markAsDone_alreadyDone_exceptionThrown() {
        try {
            Task task = new Todo("Description");
            task.markAsDone();
            task.markAsDone();
            fail();
        } catch (Exception e) {
            assertEquals("This task was marked as done before.", e.getMessage());
        }
    }

    @Test
    void serialize() throws DukeException {
        Task task = new Todo("Description");
        assertEquals("T | 0 | Description", task.serialize());
    }
}
