import duke.exception.EmptyTaskDukeException;
import duke.task.ToDo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() throws EmptyTaskDukeException {
        ToDo todo = new ToDo("Meet Rachel");
        assertEquals(todo.toString(), "[T][] Meet Rachel");
    }

    @Test
    public void doneTest() throws EmptyTaskDukeException {
        ToDo todo = new ToDo("Meet Rachel");
        todo.done();
        assertEquals(todo.toString(), "[T][+] Meet Rachel");
    }
}
