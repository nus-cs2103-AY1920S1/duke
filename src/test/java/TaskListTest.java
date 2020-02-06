import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import duke.task.Task;
import duke.command.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    ArrayList<Task> list = new ArrayList<>();
    Task task = new Task("Test");
    boolean add = this.list.add(task);
    boolean add2 = this.list.add(task);

    @Test
    public void testGetList() throws FileNotFoundException {
        assertEquals(this.list, new TaskList(this.list).getList());
    }

    @Test
    public void testGetTask() throws FileNotFoundException{
        assertEquals(this.list.get(0), new TaskList(list).getTask(0));
    }

    @Test
    public void testDeleteTask() throws FileNotFoundException {
        assertEquals(this.list.remove(0), new TaskList(this.list).deleteTask(0));
    }
}
