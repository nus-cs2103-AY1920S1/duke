package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void test_newEvent_exceptionThrown() {
        String description = "Event Test 1";
        String at = "01/01/2000 1730";
        try {
            Event event = new Event(description, at);
        } catch (InvalidDateTimeException e) {

        }
    }
}
