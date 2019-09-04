package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTaskTest {
    @Test
    void getSaveTimeString_normalTime_success() {
        String save = new TimeTask("desc", "28/8/2019 1234").getSaveTimeString();
        assertEquals("28/8/2019 1234", save);
    }

    @Test
    void getTimeString_normalTime_success() {
        String save = new TimeTask("desc", "28/8/2019 1234").getTimeString();
        assertEquals("2019 August 28 1234", save);
    }
}
