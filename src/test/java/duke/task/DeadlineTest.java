package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("return book",
                LocalDateTime.parse("2/12/2019 1800", DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        assertEquals(deadline.toString(), "[D][-] return book (by: 2nd of December 2019, 6.00PM)");
    }
}