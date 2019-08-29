import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void reprTest() {
        Todo todo = new Todo("return books", true);
        assertEquals("[T][✓] return books", todo.repr());
    }

    @Test
    public void doneTest() {
        Todo todo = new Todo("return books");
        assertEquals("  [T][✓] return books", todo.done());
    }
}
