import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testTaskListSize() {
        TaskList allTasks = new TaskList(new ArrayList<Task>());
        allTasks.addToDo("Test ToDo");
        allTasks.addEvent("Test Event", "26/02/1997 09:00", "26/02/1997 18:00");
        allTasks.addDeadline("Test Deadline", "26/02/1997 09:00");
        assertEquals(3, allTasks.size());
    }
}
