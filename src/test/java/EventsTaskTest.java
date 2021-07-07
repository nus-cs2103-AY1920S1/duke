import java.time.LocalDateTime;

import duke.tasks.EventsTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *  * A test used to test the events task class.
 */
public class EventsTaskTest {

    /**
     * Tests the ToStringFormat of the eventstask class. This ensures that the task is output 
     * per the specified formats.
     */
    @Test
    public void testToStringFormat() {
        LocalDateTime startingTimeStub = LocalDateTime.parse("2019-01-20T06:30:00");
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-01-20T07:30:00");  

        assertEquals("[E][✓] project meeting (at: 20 JANUARY 630 hours to 20 JANUARY 730 hours)", 
        new EventsTask(true, "project meeting",startingTimeStub, endingTimeStub).toString());
    }

    /**
     *  Tests the toFileFormat method of the eventstask class. This ensures that the task is
     *  saved in a proper format when being stored.
     * 
     */
    @Test
    public void testToFileFormat() {
        LocalDateTime startingTimeStub = LocalDateTime.parse("2019-01-20T06:30:01");
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-01-20T07:30:01");  

        assertEquals("E|1|project meeting|2019-01-20T06:30:01|2019-01-20T07:30:01\n", 
        new EventsTask(true, "project meeting",startingTimeStub, endingTimeStub).toFileFormat());
    }
}