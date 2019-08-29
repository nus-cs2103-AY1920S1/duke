package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] thing", new ToDo("thing").toString());
    }
}
