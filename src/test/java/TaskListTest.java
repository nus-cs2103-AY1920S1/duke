import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void check_addTask() {
        TaskList tasks = new TaskList();
        Task newTask = new ToDo("wash clothes");
        tasks.addTask(newTask);
        assertEquals(1, tasks.getTaskCount());
    }

    @Test
    public void check_deleteTask() {
        TaskList tasks = new TaskList();
        Task newTask = new ToDo("wash clothes");
        tasks.addTask(newTask);
        tasks.deleteTask(0);
        assertEquals(0, tasks.getTaskCount());
    }

    @Test
    public void mark_doneTask() {
        try {
            TaskList tasks = new TaskList();
            Task newTask = new ToDo("wash clothes");
            newTask.markAsDone();
            tasks.addTask(newTask);
            tasks.markTaskDone(0);
        } catch (Exception e) {
            assertEquals("☹ The task is already marked done.", e.getMessage());
        }

    }
}
