package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void eventGetPeriodManualInputCorrectOutput() {
        EventStub event = new EventStub("Test", "3/12/2019 1800", false);
        assertEquals("3/12/2019 1800", event.getPeriod());
    }

    @Test
    void eventToStringManualInputCorrectFormat() {
        EventStub event = new EventStub("Test", "3/12/2019 1800", false);
        assertEquals("[E][X] Test (at: 3/12/2019 1800)", event.toString());
    }
}