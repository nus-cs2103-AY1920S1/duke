package duke.command;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a JUnit test for Deadline.
 */

class DeadlineTest {

    /**
     * Test toString method in deadline.
     */

    @Test
    public void deadlineToStringFormat_validInput_success() {
        try {
            Deadline deadline = new Deadline("borrow book", "2/12/2019 18:00");
            assertEquals("[D][\u2718] borrow book (by: 2/12/2019 18:00)", deadline.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}