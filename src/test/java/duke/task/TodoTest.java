package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("A description");
        assertEquals("[T][\u2718] A description", todo.toString());
    }
}