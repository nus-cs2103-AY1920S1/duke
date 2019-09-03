import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void size() {
        Task task = new Todo("read book");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(task);
        assertEquals(1, tasks.size());
    }

    @Test
    public void delete() {
        Task task = new Todo("read book");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(task);
        tasks.delete(1);
        assertEquals(0, tasks.size());
    }
}
