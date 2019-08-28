import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void sizeTest() {
        TaskList temp = new TaskList();
        temp.add("todo read book");
        assertEquals(1, temp.getSize());
    }

    @Test
    void deleteTest() {
        TaskList temp = new TaskList();
        temp.add("todo read book");
        Task curr = temp.deleteTask(1);
        assertEquals(1, curr.getName());
    }
}
