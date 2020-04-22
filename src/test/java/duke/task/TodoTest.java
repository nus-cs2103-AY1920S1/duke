package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void shouldGenerateCorrectString() {
        Todo task = new Todo("Lorem Ipsum");

        assertEquals("[T][ ] Lorem Ipsum", task.toString());
    }
}