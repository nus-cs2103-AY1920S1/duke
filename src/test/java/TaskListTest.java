import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testTaskNumber() {
        TaskList t = new Todo(2, "[✓]", "buy bread", "todo");
        int taskNum = t.getTaskNumber();
        assertEquals(2, taskNum);
    }

    @Test
    public void testTaskName() {
        TaskList t = new Todo(2, "[✓]", "buy bread", "todo");
        String nameOfTask = t.getTaskName();
        assertEquals("buy bread", nameOfTask);
    }

    @Test
    public void testType() {
        TaskList t = new Todo(2, "[✓]", "buy bread", "todo");
        String nameOfType = t.getType();
        assertEquals("todo", nameOfType);
    }


}
