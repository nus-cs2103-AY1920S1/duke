package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void deadlineGetDeadlineCorrectInputCorrectOutput() {
        DeadlineStub deadline = new DeadlineStub("Test", "031219 1800", false);
        assertEquals("031219 1800", deadline.getDeadline());
    }
}