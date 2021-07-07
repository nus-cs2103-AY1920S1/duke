package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.model.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private Task dummyTask = new Todo("dummyTaskString");
    private Task dummyTask2 = new Todo("dummyTaskString2");
    private TaskList dummyTasks = new TaskList();

    @Test
    public void dummyAdd() {
        dummyTasks.add(dummyTask);
        assertEquals("1.[T][ ] dummyTaskString", dummyTasks.toString());
    }

    @Test
    public void dummyRemove() {
        dummyTasks.add(dummyTask);
        dummyTasks.remove(dummyTask);
        assertEquals("", dummyTasks.toString());
    }


    @Test
    public void dummyGet() {
        dummyTasks.add(dummyTask);
        assertEquals("[T][ ] dummyTaskString", dummyTasks.get(0).toString());
    }

    @Test
    public void dummySize() {
        dummyTasks.add(dummyTask);
        assertEquals(1, dummyTasks.size());
    }

    @Test
    public void dummyToString() {
        dummyTasks.add(dummyTask);
        dummyTasks.add(dummyTask2);
        assertEquals("1.[T][ ] dummyTaskString\n2.[T][ ] dummyTaskString2", dummyTasks.toString());
    }

}
