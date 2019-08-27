import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

class TodoTest {
    @Test
    void shouldGenerateCorrectString() {
        Todo task = new Todo("Lorem Ipsum");

        assertEquals("[T][ ] Lorem Ipsum", task.toString());
    }
}