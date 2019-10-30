import duke.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testCheckDescription() {
        String desc = "Checking ToDo Task";
        ToDo todo = new ToDo(desc);
        assertEquals(desc, todo.getDesc());
    }

    @Test
    public void testCheckDone() {
        String icon = "O";
        String desc = "Checking ToDo icon";
        ToDo todo = new ToDo(desc);
        todo.markAsDone();
        assertEquals(icon, todo.getStatusIcon());
    }
}
