package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("[D][X] deadline (by: 01/01/1970 07:30:00)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[D][O] deadline (by: 01/01/1970 07:30:00)", test.toString(), "markAsDone() method works");
    }
}
