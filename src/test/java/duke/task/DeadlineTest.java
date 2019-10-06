package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void correctDeadlineFormat() {
        Deadline deadline = new Deadline("desc", LocalDateTime.of(2000, 3, 21, 9, 50));
        assertEquals("[D][✘] desc (by: 2000-03-21T09:50)", deadline.toString());
    }
}
