package duke.test.Task;
import duke.task.ToDo;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void todoTypeTest_Success() throws DukeException {
        String d = "todo borrow book";
        ToDo td = new ToDo("borrow book");
        assertEquals("[T]", td.getType());
    }

    @Test
    public void todoGetDescription_Success() throws DukeException {
        String d = "todo borrow book";
        ToDo td = new ToDo("borrow book");
        assertEquals("borrow book", td.getDescription());
    }

    @Test
    public void todoToString_Success() throws DukeException {
        String d = "todo borrow book";
        String assertE = "[T]" + "[" + "\u2718" + "] borrow book";
        ToDo td = new ToDo("borrow book");
        assertEquals(assertE, td.toString());
    }
}
