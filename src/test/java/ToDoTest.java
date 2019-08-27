import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    private Task check = new ToDo("read book");

    @Test
    void testToString() {
        String expected = "[T][✘] read book";
        assertEquals(expected, check.toString());
    }

    @Test
    void toSave() {
        String expected = "T | 0 | read book";
        assertEquals(expected, check.toSave());
    }
}