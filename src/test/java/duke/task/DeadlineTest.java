package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getDeadline() {
        Deadline deadline = new Deadline("live", "2/12/2019 1800", false);
        assertEquals("2/12/2019 1800", deadline.getDeadline());
    }

    @Test
    void toString1() {
        Deadline deadline = new Deadline("live", "2/12/2019 1800", false);
        assertEquals("[D][X] live (by: Mon, 2 Dec 2019 18:00:00 GMT)", deadline.toString());
    }
}