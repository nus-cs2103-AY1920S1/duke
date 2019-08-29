import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        toDo task = new toDo("read book");
        assertEquals("[T][0] read book", task.toString());
    }

    @Test
    public void toSaveTest() {
        toDo task = new toDo("read book");
        assertEquals("T | 0 | read book", task.toSave());
    }
}
