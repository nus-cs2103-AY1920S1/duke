package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void test_ToString() throws DukeException {
        Deadline deadline = new Deadline("Quiz", "05/10/2019 2359");
        assertEquals("[D][✘] Quiz (by:Sat Oct 10 23:59:00 SGT 2019", deadline.toString());
    }

    @Test
    void text_Format() throws DukeException {
        Deadline deadline = new Deadline("Quiz", "05/10/2019 2359");
        assertEquals("D | 0 | Quiz | 05/10/2019 2359", deadline.textFormat());
    }
}