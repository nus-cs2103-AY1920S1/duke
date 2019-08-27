package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][\u2718] todo item 1",
                new ToDo("todo item 1").toString());
        assertEquals("[T][\u2713] todo item 2",
                new ToDo("todo item 2", true).toString());
    }
}
