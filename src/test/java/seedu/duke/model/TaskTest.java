package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dto.Task;
import seedu.duke.model.dto.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void markAsDone() {
        Task task = new Task("buy bread");
        assertEquals(false, task.getIsDone());
        task.markAsDone();
        assertEquals(true, task.getIsDone());
    }

    @Test
    void setErrorMessage() {
        Task task = new Task("buy bread");
        task.setErrorMsg("error test");
        assertEquals("error test", task.getErrorMsg());
    }
}
