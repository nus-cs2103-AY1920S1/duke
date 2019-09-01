import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoTest() {
        assertEquals("[T][0] A", new ToDo("A").toString());
    }

    @Test
    public void getTypeTest() {
        assertEquals("T", new ToDo("A").getType());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("A", new ToDo("A"));
    }
}
