import task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo(false, "Homework");
        String expected = "[T][✗] Homework";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToFileString() {
        ToDo todo = new ToDo(false, "Homework");
        String expected = "T | 0 | Homework";
        assertEquals(expected, todo.toFileString());
    }

}