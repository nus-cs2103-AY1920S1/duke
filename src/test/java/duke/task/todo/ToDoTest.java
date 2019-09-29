package duke.task.todo;

import duke.task.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testToString() {
        ToDo task = new ToDo("borrow books");
        String expected = "[T][✘] borrow books";
        Assertions.assertEquals(expected, task.toString());
    }
}