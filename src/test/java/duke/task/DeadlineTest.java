package duke.task;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void testDeadlineCreation() throws DukeException {
        assertEquals("[D][✗] Rest (by: 1 Sep 2019 00:00)", new Deadline("Rest", "01/09/2019 0000").toString());
    }

    @Test
    void testDeadlineDataString() throws DukeException {
        assertEquals("D | 0 | Rest | 01/09/2019 0000", new Deadline("Rest", "01/09/2019 0000").toDataString());
    }
}
