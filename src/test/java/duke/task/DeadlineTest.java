package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    void shouldGenerateCorrectString() {
        Deadline task = new Deadline("Lorem Ipsum", "12/03/2045 1234");

        assertEquals("[D][ ] Lorem Ipsum (by: 12/03/2045 1234)", task.toString());
    }
}