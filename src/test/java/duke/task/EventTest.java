import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

class EventTest {
    @Test
    void shouldGenerateCorrectString() {
        Event task = new Event("Lorem Ipsum", "12/03/2045 1234");

        assertEquals("[E][ ] Lorem Ipsum (at: 12/03/2045 1234)", task.toString());
    }
}