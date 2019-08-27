import tasks.Event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToStringFormat() {
        assertEquals("[E][\u2718] play basketball with Yu Jie (at: 17 December 2019, 10:20AM)",
                new Event("play basketball with Yu Jie",
                        LocalDateTime.parse("17/12/2019 1020", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                false).toString());
    }

    @Test
    public void testFileStringFormat() {
        assertEquals("E | 1 | eat dinner with Spider-man | 1 January 2020, 7:00PM",
                new Event("eat dinner with Spider-man",
                        LocalDateTime.parse("01/01/2020 1900", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                true).fileString());
    }
}