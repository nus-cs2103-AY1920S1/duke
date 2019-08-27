import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    UI ui = new UI();

    @Test
    void testTaskList_AddOneTask() {
        TaskList tasks = new TaskList(ui);
        tasks.addTask(new ToDo("testDescription", false));
        assertEquals(tasks.getSize(), 1);
    }

    @Test
    void testTaskList_AddAndDeleteOneTask() throws DukeException {
        TaskList tasks = new TaskList(ui);
        tasks.addTask(new ToDo("testDescription", false));
        tasks.deleteTask(1);
        assertEquals(tasks.getSize(), 0);
    }
}
