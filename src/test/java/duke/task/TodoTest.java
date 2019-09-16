package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("test");
        assertEquals("[T][X] test", todo.toString());
    }
}