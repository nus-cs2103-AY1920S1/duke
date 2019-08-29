import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addDeleteTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("This is a Todo."));
            taskList.addTask(new Deadline("This is a Deadline.", "22/02/2020 22:22"));
            taskList.addTask(new Event("This is an Event.", "22/02/2020 22:22"));
        } catch (DukeException e) {
            // Fail but for other reasons
            fail(e.getMessage());
        }

        try {
            taskList.deleteTask(2);
            taskList.deleteTask(0);
            taskList.deleteTask(0);
        } catch (DukeException e) {
            fail(e.getMessage());
        }

        try {
            taskList.deleteTask(3);
            fail();
        } catch (DukeException e) {
            // Pass
        }
    }    
}