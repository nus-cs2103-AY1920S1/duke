import org.junit.jupiter.api.Test;
import duke.task.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTest() {
        Todo todo = new Todo("make some money");
        assertEquals("[ T ][ X ] make some money", todo.toString());
        assertEquals("todo", todo.getType());
    }

}
