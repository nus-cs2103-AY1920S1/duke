import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testEmptySizedTaskList() {
        assertEquals(new TaskList().size(), 0);
    }

    @Test
    public void testLastTaskAdded() {
        TaskList tl = new TaskList();
        Todo todoTask = new Todo("return book");
        tl.add(todoTask);
        assertEquals(tl.getLast(), todoTask);
    }

    @Test
    public void testTaskPrint() {
        TaskList tl = new TaskList();
        Todo todoFirstTask = new Todo("return book");
        Todo todoSecondTask = new Todo("borrow book");
        tl.add(todoFirstTask);
        tl.add(todoSecondTask);
        assertEquals(tl.taskPrint(1), todoSecondTask);
    }
}
