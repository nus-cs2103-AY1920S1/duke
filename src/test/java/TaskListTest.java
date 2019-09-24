import com.TaskList;
import com.tasks.Task;
import com.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

//    @Test
//    void addTask() {
//
//    }
//
//    @Test
//    void markTaskDone() {
//
//    }
//
//    @Test
//    void deleteTask() {
//
//    }

    @Test
    // NOTE: Seems like its more productive to test other methods
        // which are more complicated?
    void getNumTasks() {
        ArrayList<Task> taskArr = new ArrayList<Task>();
        taskArr.add(new ToDo("finish cs2103 iP"));
        taskArr.add(new ToDo("read AP3 for cs2103 tP"));
        taskArr.add(new ToDo("eat chocolate"));

        assertEquals(3, new TaskList(taskArr).getNumTasks());
    }

}
