package tasks;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import utils.StringToDate;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    private Deadline deadline;

    DeadlineTest() throws ParseException, DukeException {
        this.deadline = new Deadline("description", new StringToDate("10-10-2019 18:00"));
    }

    @Test
    void printForStorage_taskObject_formattedTaskString() {
        assertEquals("D | 0 | description | 10-10-2019 18:00", deadline.printForStorage());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[D][✗] description (by: 10-10-2019 18:00)", deadline.toString());
    }

}
