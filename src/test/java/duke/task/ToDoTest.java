package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testConvertTaskToString() {
        assertEquals("T | 0 | homework", (new ToDo("homework").convertTaskToString()));
    }

    @Test
    public void testToString() {
        assertEquals("[T] homework", (new ToDo("homework").toString()));
    }
}
