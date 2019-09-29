package duke.memo.task;

import duke.memo.record.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void todoTest() throws Throwable {
        ToDo todo = new ToDo("read book");
        assertEquals("[T]✘ read book", todo.toString());
        todo.taskDone();
        assertEquals("[T]✓ read book", todo.toString());
    }

    @Test
    void todoGetPrintableMsgTest() throws Throwable {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.getPrintableMsg());
        todo.taskDone();
        assertEquals("T | 1 | read book", todo.getPrintableMsg());
    }
}