package duke.task;

import duke.command.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    @Test
    public void deadlineGenerateStringFormat_doneDeadline_success() {
        Deadline deadline = new Deadline("Certain CS project", true, "one of these days");
        assertEquals("D | 1 | Certain CS project | one of these days", deadline.getFileStringFormat());
    }
}