import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoSavingAsString_todo_expectedString() {
        Task t = new Todo("buy groceries");
        assertEquals("T | 0 | buy groceries | 0", t.toSave());

        t.markAsDone();
        assertEquals("T | 1 | buy groceries | 0", t.toSave());
    }

    @Test
    public void todoPrintingAsString_todo_expectedString() {
        Task t = new Todo("buy groceries");
        assertEquals("[T][\u2718] buy groceries", t.toString());

        t.markAsDone();
        assertEquals("[T][\u2713] buy groceries", t.toString());
    }
}
