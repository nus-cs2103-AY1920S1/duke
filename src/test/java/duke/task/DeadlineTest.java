package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getDate() {
        Deadline deadline = new Deadline("A description", "20/12/2019 0000");
        assertEquals("20/12/2019 0000", deadline.getDate());
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("A description", "20/12/2019 0000");
        assertEquals("[D][✘] A description (by: December 20, 2019 at 0000)", deadline.toString());
    }
}