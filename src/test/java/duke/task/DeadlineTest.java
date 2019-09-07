package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {
    @Test
    void deadlineTestWithString() {
        Deadline test = new Deadline("deadline", "deadlineBy");
        assertEquals("[D][X] deadline (by: deadlineBy)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[D][O] deadline (by: deadlineBy)", test.toString(), "markAsDone() method works");
    }

    @Test
    void deadlineTestWithDate() {
        Deadline test = new Deadline("deadline", new Date(0));
        assertTrue(test.toString().contains("[D][X] deadline"), "toString() method works");

        test.markAsDone();
        assertTrue(test.toString().contains("[D][O] deadline"), "markAsDone() method works");
    }
}
