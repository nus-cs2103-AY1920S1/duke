package duke.task;

import duke.exception.DukeException;
import duke.util.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    private Event event;

    @BeforeEach
    public void initialize() throws DukeException {
        this.event = new Event("Fly to the moon",
                DateTime.parseString("11/11/3019 0000"));
    }

    @Test
    public void toString_methodCall_formattedOutput() {
        assertEquals("[E][✘] Fly to the moon (at: 11 Nov 3019, 12:00 AM)",
                this.event.toString());
    }

    @Test
    public void markAsDone_methodCall_formattedOutput() {
        this.event.markAsDone();
        assertEquals("[E][✓] Fly to the moon (at: 11 Nov 3019, 12:00 AM)",
                this.event.toString());
    }
}