package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void testToDoCreation() {
        assertEquals("[T][✗] Rest", new ToDo("Rest").toString());
    }

    @Test
    void testToDoDataString() {
        assertEquals("T | 0 | Rest", new ToDo("Rest").toDataString());
    }
}
