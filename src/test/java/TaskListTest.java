import org.junit.jupiter.api.Test;

import tasks.TaskList;
import tasks.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    void getNumOfTasks() {
        TaskList task = new TaskList();
        task.addTask(new ToDos("do 2103"));
        task.addTask(new ToDos("do 2101"));
        assertEquals(2, task.getNumOfTasks());
    }
}
