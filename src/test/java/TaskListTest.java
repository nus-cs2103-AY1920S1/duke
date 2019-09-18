import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    public TaskList constructTaskList() {

        ArrayList<Task> list = new ArrayList<>();

        try {
            SimpleDateFormat parser1 = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = parser1.parse("8/9/2019");
            LocalTime time1 = LocalTime.of(00, 00);
            Event e = new Event("test", date1, time1);

            SimpleDateFormat parser2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date2 = parser2.parse("2/12/2019");
            LocalTime time2 = LocalTime.of(23, 59);
            Deadline d = new Deadline("test", date2, time2);

            list.add(new Todo("test"));
            list.add(e);
            list.add(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TaskList(list);
    }

    @Test
    public void testGet() {
        TaskList list = constructTaskList();
        assertEquals(new Todo("test"), list.get(0));
    }

    @Test
    public void testSize() {
        TaskList list = constructTaskList();
        assertEquals(3, list.size());
    }

    @Test
    public void testDelete() {
        TaskList list = constructTaskList();
        assertEquals(new Todo("test"), list.delete(0));
    }
}
