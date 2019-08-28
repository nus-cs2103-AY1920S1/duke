package duke.task.deadline;

import duke.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void getBy() {
        Deadline deadline = new Deadline("return books", "2nd of September 2019, 6:00pm");
        String expected = "2nd of September 2019, 6:00pm";
        Assertions.assertEquals(expected, deadline.getBy());
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("return books", "2nd of September 2019, 6:00pm");
        String expected = "[D][✘] return books (by: 2nd of September 2019, 6:00pm)";
        Assertions.assertEquals(expected, deadline.toString());
    }
}