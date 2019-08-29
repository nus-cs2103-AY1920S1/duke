import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    public void addTaskTest() {
        Todo dummy = new Todo("Dummy Test");

        TaskList tasks = new TaskList();

        for (int i = 0; i < 5; ++i) {
            tasks.addTask(dummy);
        }

        assertEquals(tasks.size(), 5);
    }
}
