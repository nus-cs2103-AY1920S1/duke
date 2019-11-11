package seedu.duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {
    @Test
    public void toString_format() {
        String description = "example deadlineTask";
        String deadlineInfo = "next Monday";
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineInfo);

        assertTrue(deadlineTask.toString().matches("^\\[\\d{5}\\]\\[D\\]\\[[OX]\\] .*$"));

        String stringValue = deadlineTask.toString().split("] ", 2)[1];
        assertEquals(stringValue, description + " (by: " + deadlineInfo + ")");
    }

    @Test
    public void toString_status() {
        String description = "example deadlineTask";
        String deadlineInfo = "next Monday";
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineInfo);

        String isDoneStr = deadlineTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "X");

        deadlineTask.markAsDone();
        isDoneStr = deadlineTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "O");
    }

    @Test
    public void toString_timeFormat_stringReturned() {
        String description = "example deadlineTask";
        String deadlineInfo = "02/01/2019 22:10";
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineInfo);

        String stringValue = deadlineTask.toString().split("] ", 2)[1];
        String expectedValue = description + " (by: January 2, 2019, 10:10 PM)";

        assertEquals(stringValue, expectedValue);
    }
}
