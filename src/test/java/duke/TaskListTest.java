package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private TaskList t = new TaskList();

    @Test
    public void addToListTest() {
        t.addToList(new Task("generic 1", false));
        t.addToList(new Task("generic 2", false));
        t.addToList(new Task("generic 3", true));
        t.addToList(new Task("generic 4", true));
        assertEquals("[ ] generic 1", t.getList().get(2).toString());
        assertEquals("[ ] generic 2", t.getList().get(3).toString());
        assertEquals("[ ] generic 3", t.getList().get(1).toString());
        assertEquals("[ ] generic 4", t.getList().get(0).toString());
    }

    @Test
    public void getListSizeTest() {
        t.addToList(new Task("generic 1", false));
        t.addToList(new Task("generic 2", false));
        t.addToList(new Task("generic 3", false));
        t.addToList(new Task("generic 4", false));
        assertEquals(4, t.getListSize());
    }

    @Test
    public void markAsDoneTest() {
        t.addToList(new Task("generic 1", false));
        t.addToList(new Task("generic 2", false));
        t.addToList(new Task("generic 3", false));
        t.addToList(new Task("generic 4", false));
        t.markAsDone(1);
        t.markAsDone(3);
        assertEquals("[+] generic 1", t.getList().get(0).toString());
        assertEquals("[+] generic 3", t.getList().get(2).toString());
        try {
            t.markAsDone(5);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 4 out of bounds for length 4", e.getMessage());
        }
    }

    @Test
    public void deleteTest() {
        t.addToList(new Task("generic 1", false));
        t.addToList(new Task("generic 2", false));
        t.addToList(new Task("generic 3", false));
        t.addToList(new Task("generic 4", false));
        t.delete(1);
        t.delete(1);
        t.delete(1);
        t.delete(1);
        assertEquals(0, t.getListSize());
    }
}
