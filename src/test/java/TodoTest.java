import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo = new Todo("read book");
    @Test
    public void TodoGetInformationTest() {
        assertEquals("", todo.getInformation());
    }

    @Test
    public void TodoGetDescriptionTest() {
        assertEquals("read book", todo.getDescription());
    }
}
