import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testGetList() {
        ArrayList<Task> test = new ArrayList<>();
        TaskList list = new TaskList(test);
        assertEquals(test, list.getList());
    }

    @Test
    public void testAddList() {
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Task("x"));
        TaskList list = new TaskList(test);
        assertEquals(test, list.getList());
    }
}
