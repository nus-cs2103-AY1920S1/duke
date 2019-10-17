import main.DukeException;
import main.TaskList;
import org.junit.jupiter.api.Test;
import task.Deadlines;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDateTime() {
        try {
            assertEquals("10/10/2001 1000",
                    TaskList.localDateTimeToString(TaskList.dateTimeParser("10/10/2001 1000")));
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDeadlines() {
        try {
            assertEquals(false,
                    new Deadlines("test",
                            TaskList.dateTimeParser("10/10/1000 1000"), false).isDone());
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDukeException() {
        assertEquals("hi", new DukeException("hi").getMessage());
    }
}
