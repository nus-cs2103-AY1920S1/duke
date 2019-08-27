import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private Storage storage;
    private TaskList taskList;


    @Test
    public void TaskListTest() {
        taskList = new TaskList();
        Task task = new Todo("task1");
        taskList.addTask(task);
        assertEquals(task, taskList.getTaskList().get(0));
        taskList.addTask(new Todo("task2"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void ExitTest() {
        Command c = new ExitCommand();
        assertEquals(true, c.isExit());
    }
}