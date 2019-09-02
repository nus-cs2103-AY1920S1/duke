package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests Deadline class.
 */
class DeadlineTest {
    /**
     * Tests if Deadline.getFileStringFormat outputs the correct String format.
     */
    @Test
    public void deadlineGenerateStringFormat_doneDeadline_success() {
        Deadline deadline = new Deadline("Certain CS project", true, "one of these days");
        assertEquals("D | 1 | Certain CS project | one of these days", deadline.getFileStringFormat());
    }
}