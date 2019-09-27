package duke.test;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion1() {
        assertEquals("[D][x] homework (by: 29/12/2019 2359)",
                (new Deadline("homework", "29/12/2019 2359")).toString());
    }

    @Test
    public void testStringConversion2() {
        assertEquals("[D][x] homework (by: 1/01/2019 0000)",
                (new Deadline("homework", "1/01/2019 0000")).toString());
    }
}
