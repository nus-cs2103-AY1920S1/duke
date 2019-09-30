package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTaskTest {
    private TimeTask getTimeTask() {
        return new TimeTask("desc", "28/8/2019 1234") {
            @Override
            String getTaskType() {
                return "type";
            }
        };
    }

    @Test
    void getSaveTimeString_normalTime_success() {
        assertEquals("28/8/2019 1234", getTimeTask().getSaveTimeString());
    }

    @Test
    void getTimeString_normalTime_success() {
        assertEquals("2019 August 28 1234", getTimeTask().getTimeString());
    }
}
