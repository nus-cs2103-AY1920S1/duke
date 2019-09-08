package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void deadlineGetDeadlineCorrectInputCorrectOutput() {
        DeadlineStub deadline = new DeadlineStub("Test", "3/12/2019 1800", false);
        assertEquals("3/12/2019 1800", deadline.getDeadline());
    }

    @Test
    void deadlineToStringManualInputCorrectFormat() {
        DeadlineStub deadline = new DeadlineStub("Test", "3/12/2019 1800", false);
        assertEquals("[D][X] Test (by: Tue, 3 Dec 2019 18:00:00 GMT)", deadline.toString());
    }
}