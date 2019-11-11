package seedu.duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTaskTest {
    @Test
    public void toString_format() {
        String description = "example eventTask";
        String eventInfo = "random location";
        EventTask eventTask = new EventTask(description, eventInfo);

        assertTrue(eventTask.toString().matches("^\\[\\d{5}\\]\\[E\\]\\[[OX]\\] .*$"));

        String stringValue = eventTask.toString().split("] ", 2)[1];
        assertEquals(stringValue, description + " (at: " + eventInfo + ")");
    }

    @Test
    public void toString_status() {
        String description = "example eventTask";
        String eventInfo = "random location";
        EventTask eventTask = new EventTask(description, eventInfo);

        String isDoneStr = eventTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "X");

        eventTask.markAsDone();
        isDoneStr = eventTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "O");
    }
}
