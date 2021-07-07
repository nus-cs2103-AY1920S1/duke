package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.model.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    private Task dummyTask = new Todo("dummyTaskString");
    private TaskList dummyTasks = new TaskList();

    @Test
    public void dummyAddedMessage() {
        dummyTasks.add(dummyTask);
        String s = "Got it. I've added this task:\n  [T][ ] dummyTaskString\n"
                + "Now you have 1 task in the list.";
        assertEquals(s, dummyTask.addedMessage(dummyTasks));
    }

    @Test
    public void dummyDeletedMessage() {
        dummyTasks.add(dummyTask);
        dummyTasks.remove(dummyTask);
        String s = "Noted. I've removed this task:\n[T][ ] dummyTaskString\n"
                + "Now you have 0 tasks in the list.";
        assertEquals(s, dummyTask.deletedMessage(dummyTasks));
    }

    @Test
    public void dummyDoneMessage() {
        dummyTask.setIsDone(true);
        String s = "Nice! I've marked this task as done:\n  [T][+] dummyTaskString";
        assertEquals(s, dummyTask.doneMessage());
    }

    @Test
    public void dummyGetTaskString() {
        assertEquals("dummyTaskString", dummyTask.getTaskString());
    }

    @Test
    public void dummyGetIsDone() {
        dummyTask.setIsDone(true);
        assertEquals(true, dummyTask.getIsDone());
    }

    @Test
    public void dummyGetType() {
        assertEquals(Task.Type.TODO, dummyTask.getType());
    }

    @Test
    public void dummyGetDateAndTime() {
        assertEquals(null, dummyTask.getDateAndTime());
    }

    @Test
    public void dummyToString() {
        assertEquals("[T][ ] dummyTaskString", dummyTask.toString());
    }
}
