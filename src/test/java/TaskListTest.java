import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void convertTasksToString() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("go to school", TaskType.TODO));
        TaskList taskList = new TaskList(tasks);

        assertEquals("T | false | go to school | null\n", taskList.convertTasksToString());
    }
}