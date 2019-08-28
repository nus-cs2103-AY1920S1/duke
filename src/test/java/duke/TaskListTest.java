package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test() throws DukeException {
        TaskList test = new TaskList();
        test.addTask(Todo.of("borrow book"));
        assertEquals(test.getNumTasks(), 1);
        String done = test.doneTask(0);
        assertEquals(done, "  [T][" + "\u2713" + "] borrow book");
        test.deleteTask(0);
        assertEquals(test.getNumTasks(), 0);
    }
}
