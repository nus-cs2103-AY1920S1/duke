import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void toDoTest() {
        System.out.println("TodoTest starts");
        ToDo todo = new ToDo("testing");
        assertEquals("[T][N] testing", todo.toString());
        assertEquals("T | 0 | testing", todo.toWriteFile());
        System.out.println("TodoTest Pass");
    }
}


