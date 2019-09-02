package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void stringConversion_withTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2019 0700");
        assertEquals("[D][✘] Test (by: Tue, 1 January 2019, 07:00AM)", deadline.toString());
    }

    @Test
    void stringConversion_withoutTime() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2019");
        assertEquals("[D][✘] Test (by: Tue, 1 January 2019)", deadline.toString());
    }

    @Test
    void encode() throws DukeException {
        Deadline deadline = new Deadline("Test", "01/01/2019 0700");
        assertEquals("D | 0 | Test | 01/01/2019 0700", deadline.encode());
    }
}
