import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void taskListTest() {
        TaskList tasklist = new TaskList(new ArrayList<>());
        assertEquals(0, tasklist.getTaskSize());
        tasklist.addTask(new Todo("Todo Description"));
        assertEquals(1, tasklist.getTaskSize());
        assertEquals(tasklist.getList().get(0), tasklist.getTaskByIndex(1));
        tasklist.removeTaskByIndex(1);
        assertEquals(0, tasklist.getTaskSize());
    }

}