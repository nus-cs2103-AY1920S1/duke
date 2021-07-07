package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private Todo dummyTodo = new Todo("dummyString");
    private Todo dummyTodo2 = new Todo("dummyString2", true);

    @Test
    public void dummyToString() {
        assertEquals("[T][ ] dummyString", dummyTodo.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[T][+] dummyString2", dummyTodo2.toString());
    }

}
