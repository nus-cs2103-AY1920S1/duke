import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTask(){
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);

        taskList.addTask(new Task("borrow book", false));
        assertEquals(1, taskList.getListOfTasks().size());
    }

}