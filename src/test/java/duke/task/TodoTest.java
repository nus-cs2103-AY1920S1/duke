package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    public void dummyTodoTest() throws DukeException {
        Todo test = Todo.of("borrow book");
        assertEquals(test.toString(), "  [T][" + "\u2718" + "] borrow book");
    }

}