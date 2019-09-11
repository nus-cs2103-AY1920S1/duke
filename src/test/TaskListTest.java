import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    void size() {
        try {
            taskList.add(new Todo("todo lab assignment"));
            taskList.add(new Deadline("deadline lab assignment /by 19/09/2019 0000"));
            taskList.add(new Event("event trip to taiwan /at 20/10/2019 1200"));
            if (taskList.size() != 3) {
                fail();
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void add() {
        try {
            taskList.add(new Todo("todo lab assignment"));
            taskList.add(new Deadline("deadline lab assignment /by 19/09/2019 0000"));
            taskList.add(new Event("event trip to taiwan /at 20/10/2019 1200"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void remove() {
        try {
            taskList.add(new Todo("todo lab assignment"));
            taskList.add(new Deadline("deadline lab assignment /by 19/09/2019 0000"));
            taskList.add(new Event("event trip to taiwan /at 20/10/2019 1200"));
            taskList.remove(0);
            taskList.remove(0);
            taskList.remove(0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}