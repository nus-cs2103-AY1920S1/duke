import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTask_emptyList() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);

        taskList.addTask(new Task("borrow book", false));
        assertEquals(1, taskList.getListOfTasks().size());
    }

    @Test
    public void deleteTask_oneTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Task("borrow book", false));
        TaskList taskList = new TaskList(list);

        assertEquals(1, taskList.getListOfTasks().size());
        taskList.deleteTask(0);
        assertEquals(0, taskList.getListOfTasks().size());
    }
}