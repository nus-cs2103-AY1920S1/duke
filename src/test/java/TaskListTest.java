
import duke.TaskList;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {


    @Test
    public void testGetList() {
        ArrayList<Task> test = new ArrayList<>();
        TaskList list = new TaskList(test);
        assertEquals(test, list.getList());
    }

    @Test
    public void addTaskTest() {
        Task testTask = new Todo("x");
        ArrayList<Task> test = new ArrayList<>();
        test.add(testTask);
        TaskList tasklist = new TaskList();
        tasklist.addTask(testTask);
        assertEquals(test, tasklist.getList());
    }

    @Test
    public void deleteTaskTest() {
        Task testTask = new Todo("x");
        ArrayList<Task> test = new ArrayList<>();
        test.add(testTask);
        TaskList tasklist = new TaskList(test);
        tasklist.deleteTask(1);
        assertEquals(test, tasklist.getList());
    }

    @Test
    public void markTaskTest() {
        Task testTask = new Todo("x");
        ArrayList<Task> test = new ArrayList<>();
        test.add(testTask);
        TaskList tasklist = new TaskList(test);
        Task t = tasklist.getTask(1);
        t.markAsDone();
        assertEquals(t.getStatusIcon(), tasklist.getTask(1).getStatusIcon());
    }

}
