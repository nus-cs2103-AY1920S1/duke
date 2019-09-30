import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void getTaskListTest() {
        ArrayList<Task> testList = new ArrayList<>();
        TaskList taskList = new TaskList(testList);
        assertEquals(testList, taskList.getTaskList());
    }

    @Test
    public void addTaskTest() {
        Task testTask = new Todo("test");
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(testTask);
        TaskList taskList = new TaskList();
        taskList.addTask(testTask);
        assertEquals(testList, taskList.getTaskList());
    }

    @Test
    public void removeTaskTest() {
        Task testTask = new Todo("test");
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(testTask);
        TaskList taskList = new TaskList(testList);
        taskList.removeTask(0);
        assertEquals(testList, taskList.getTaskList());
    }

    @Test
    public void getTaskTest() {
        Task testTask = new Todo("test");
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(testTask);
        TaskList taskList = new TaskList(testList);
        assertEquals(testTask, taskList.getTask(0));
    }

    @Test
    public void getSizeTest() {
        Task testTask = new Todo("test");
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(testTask);
        TaskList taskList = new TaskList(testList);
        assertEquals(testList.size(), taskList.getSize());
    }
}
