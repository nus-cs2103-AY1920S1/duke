package seedu.duke;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void Test() {
        ArrayList<Task> arr = new ArrayList<>();
        TaskList tl = new TaskList(arr);
        assertEquals(0, tl.size());
    }

}
