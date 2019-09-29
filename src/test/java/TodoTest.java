import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    public void todoTest() {
        Task todo = new Todo("Todo Description");
        assertEquals("Todo Description", todo.getDescription());
        assertEquals("T | 0 | Todo Description", todo.fileFormat());
        assertEquals("[T][x] Todo Description", todo.toString());
        assertEquals("[x]", todo.getStatusIcon());
        todo.markAsDone();
        assertEquals("T | 1 | Todo Description", todo.fileFormat());
        assertEquals("[done]", todo.getStatusIcon());
        assertEquals("[T][done] Todo Description", todo.toString());
    }

}
