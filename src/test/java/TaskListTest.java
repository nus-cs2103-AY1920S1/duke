import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testPrintTasks() throws DukeException {
        TaskList tasks = new TaskList();
        Task deadlineTask = new Deadline("buy grocery", "02/12/2019 1900");
        Task eventTask = new Events("team meeting", "02/12/2019 1900-2000");
        tasks.add(deadlineTask);
        tasks.add(eventTask);

        assertEquals("1. [D][✗] buy grocery  (by: Mon Dec 02 19:00:00 SGT 2019)" + "\n" + "2. [E][✗] team meeting (at: Mon Dec 02 20:00:00 SGT 2019)" + "\n", tasks.printTaskList());
    }
}
