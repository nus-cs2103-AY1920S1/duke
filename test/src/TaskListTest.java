import duke.DirectProcessor.TaskList;
import duke.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList tl = new TaskList();

    @Test
    public void addTaskTest() {
        try {
            tl.addTask(new Todo("Eat dinner"));
            tl.addTask(new Event("Drink beer", "19/08/2019 20:00:00"));
            tl.addTask(new Deadline("Software Engineering Project",
                    "20/08/2019 00:00:00"));
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[T][\u2715] Eat dinner", check.get(0));
            assertEquals("2.[E][\u2715] Drink beer (at: 19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[D][\u2715] Software Engineering Project (by: 20/08/2019 00:00:00)",
                    check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void finishTaskTest() {
        try {
            tl.addTask(new Todo("Eat dinner"));
            tl.addTask(new Event("Drink beer", "19/08/2019 20:00:00"));
            tl.addTask(new Deadline("Software Engineering Project",
                    "20/08/2019 00:00:00"));
            tl.finishTask(2);
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[T][\u2715] Eat dinner", check.get(0));
            assertEquals("2.[E][\u2713] Drink beer (at: 19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[D][\u2715] Software Engineering Project (by: 20/08/2019 00:00:00)",
                    check.get(2));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void deleteTaskTest() {
        try {
            tl.addTask(new Todo("Eat dinner"));
            tl.addTask(new Event("Drink beer", "19/08/2019 20:00:00"));
            tl.addTask(new Deadline("Software Engineering Project",
                    "20/08/2019 00:00:00"));
            tl.finishTask(2);
            tl.deleteTask(3);
            ArrayList<String> check = tl.listAllTask();
            assertEquals("1.[T][\u2715] Eat dinner", check.get(0));
            assertEquals("2.[E][\u2713] Drink beer (at: 19/08/2019 20:00:00)", check.get(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }
}
