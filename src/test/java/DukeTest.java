
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTask() {
        Task task = new Task("Tutorial 1");
        assertEquals("Tutorial 1", task.getDescription());
        System.out.println("All task tests passed.");
    }

    @Test
    public void testEvent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
        Date date = sdf.parse("31 May 2020 00:00");
        Event event = new Event("His Birthday", date);
        event.markAsDone();
        assertEquals("Event", event.getType());
        assertEquals(1, event.getStatusNum());
        System.out.println("All event tests passed.");
    }
}
