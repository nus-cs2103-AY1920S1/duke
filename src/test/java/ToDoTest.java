import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testToString() {
        ToDo todo = new ToDo("Count stars");
        assertEquals("[T][ ] Count stars", todo.toString());
    }
}