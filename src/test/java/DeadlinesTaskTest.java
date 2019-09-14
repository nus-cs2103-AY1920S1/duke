import duke.tasks.DeadlinesTask;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * A test used to test the deadline task class.
 */
public class DeadlinesTaskTest {

    /**
     * Tests the ToStringFormat of the deadlinetask class. This ensures that the task is output 
     * per the specified formats.
     * 
     */
    @Test
    public void testToStringFormat() {
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-03-21T20:50:00");  

        assertEquals("[D][✓] return book (by: 21 MARCH 2050 hours)", 
        new DeadlinesTask(true, "return book", endingTimeStub).toString());
    }

    /**
     *  Tests the toFileFormat method of the deadlinestask class. This ensures that the task is
     *  saved in a proper format when being stored.
     * 
     */
    @Test
    public void testToFileFormat() {
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-03-21T20:50:00");  

        assertEquals("D|1|return book|2019-03-21T20:50\n", 
        new DeadlinesTask(true, "return book", endingTimeStub).toFileFormat());
    }
}