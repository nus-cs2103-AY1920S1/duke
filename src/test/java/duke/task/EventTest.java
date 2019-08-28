package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event_undoneToString_correctOutput() {
        Event event = new Event("lunch", "11/11/1111 1234");
        assertEquals("[E][\u2718] lunch (at: 11 Nov 1111, 12:34:00 PM)",
                event.toString());

    }
}
