package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("A description");
        assertEquals("[T][\u2718] A description", todo.toString());
    }
}