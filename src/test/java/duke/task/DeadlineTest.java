package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void deadlineGetDeadlineCorrectInputCorrectOutput() {
        DeadlineStub deadline = new DeadlineStub("Test", "031219 1800", false);
        assertEquals("031219 1800", deadline.getDeadline());
    }

//    @Test
//    void deadlineToStringManualInputCorrectFormat() {
//        // remove dependency
//        DeadlineStub deadline = new DeadlineStub("Testing", "031219 1800", false);
//        assertEquals("[D][X] Testing (by: 03 December 2019, 06:00PM, GMT+8)", deadline.toString());
//    }
}