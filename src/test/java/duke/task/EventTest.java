package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final String DESCRIPTION = "run";
    private static final LocalDateTime AT = LocalDateTime.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh.mma");

    @Test
    public void stringConversion() {
        assertEquals(String.format("[E][✘] %s (at: %s)", DESCRIPTION, DATE_TIME_FORMATTER.format(AT)),
                new Event(DESCRIPTION, AT).toString());
    }

    @Test
    public void getTime() {
        assertEquals(DATE_TIME_FORMATTER.format(AT), new Event(DESCRIPTION, AT).getTime());
    }
}
