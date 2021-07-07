import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.sheet.Sheet;
import duke.task.Task;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SheetTest {

    @Test
    public void addTest() {
        List<Task> lst1 = new ArrayList<>();
        List<Task> lst2 = new ArrayList<>();
        Task t1 = new Todo("task1");
        Task t2 = new Todo("task2");
        lst1.add(t1);
        lst2.add(t1);
        lst2.add(t2);

        Sheet sh1 = new Sheet(lst1);
        Sheet sh2 = new Sheet(lst2);
        sh1.add(t2);
        assertEquals(sh2.toString(), sh1.toString());
    }

    @Test
    public void deleteTest() {
        List<Task> lst1 = new ArrayList<>();
        List<Task> lst2 = new ArrayList<>();
        Task t1 = new Todo("task1");
        Task t2 = new Todo("task2");
        lst1.add(t1);

        Sheet sh1 = new Sheet(lst1);
        Sheet sh2 = new Sheet(lst2);
        sh1.delete(1);
        assertEquals(sh2.toString(), sh1.toString());
    }

    @Test
    public void markAsDoneTest() {
        List<Task> lst1 = new ArrayList<>();
        List<Task> lst2 = new ArrayList<>();
        Task t1 = new Todo("task1").finish();
        Task t2 = new Todo("task2");
        lst1.add(t1);
        lst2.add(t1);

        Sheet sh1 = new Sheet(lst1);
        Sheet sh2 = new Sheet(lst2);
        sh2.markAsDone(1);
        assertEquals(sh2.toString(), sh1.toString());
    }

    @Test
    public void isEmptyTest() {
        List<Task> lst1 = new ArrayList<>();
        Sheet sh1 = new Sheet(lst1);
        String isEmpty = "false";
        if (sh1.isEmpty()) {
            isEmpty = "true";
        }
        assertEquals("true", isEmpty);

    }
}
