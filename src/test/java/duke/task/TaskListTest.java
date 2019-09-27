package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for TaskList.
 */
public class TaskListTest {
    @Test
    public void test_newTaskList() {
        TaskList taskList = new TaskList();
        assertNotNull(taskList);
    }

    @Test
    public void test_addTask() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("task 1");
        taskList.addTask(todo);
        assertEquals(1, taskList.getTotalTasks());
    }

    @Test
    public void test_deleteTask() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("task 1");
        taskList.addTask(todo);
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTotalTasks());
    }

    @Test
    public void test_doTask() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("task 1");
        taskList.addTask(todo);
        Task task = taskList.doTask(1);
        assertEquals(task, todo);
        assertTrue(task.isDone());
    }

    @Test
    public void test_filterByString() {
        TaskList taskList = new TaskList();
        Task todo1 = new ToDo("task 1");
        Task todo2 = new ToDo("task 2");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        TaskList filteredList = taskList.filterByString("1");
        assertEquals(1, filteredList.getTotalTasks());
        assertEquals(todo1, filteredList.getTask(1));
    }
}

