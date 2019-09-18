import tasks.Deadline;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToStringFormat() {
        assertEquals("[D][\u2713] submit cs2100 homework (by: 12 December 2012, 11:59PM)",
                new Deadline("submit cs2100 homework",
                        LocalDateTime.parse("12/12/2012 2359", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                true).toString());
    }

    @Test
    public void testFileStringFormat() {
        assertEquals("D | 0 | collect passport | 7 February 2020, 8:15AM",
                new Deadline("collect passport",
                        LocalDateTime.parse("07/02/2020 0815", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                false).fileString());
    }
}