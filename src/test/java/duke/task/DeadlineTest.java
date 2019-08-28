package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void event_undoneToString_correctOutput() {
        Deadline deadline = new Deadline("lunch", "11/11/1111 1234");
        assertEquals("[D][\u2718] lunch (by: 11 Nov 1111, 12:34:00 PM)",
                    deadline.toString());

    }
}
