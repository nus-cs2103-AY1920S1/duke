package duke;

import duke.directprocessor.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
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
            assertEquals("1.[T][X] Eat dinner", check.get(0));
            assertEquals("2.[E][X] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[D][X] Software Engineering Project (by: \n  20/08/2019 00:00:00)",
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
            assertEquals("1.[T][X] Eat dinner", check.get(0));
            assertEquals("2.[E][V] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(1));
            assertEquals("3.[D][X] Software Engineering Project (by: \n  20/08/2019 00:00:00)",
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
            assertEquals("1.[T][X] Eat dinner", check.get(0));
            assertEquals("2.[E][V] Drink beer (at: \n  19/08/2019 20:00:00)", check.get(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }
}
