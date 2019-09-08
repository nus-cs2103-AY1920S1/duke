import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTaskTest {
    @Test
    public void testToStringFormat() {
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-03-21T20:50:00");  

        assertEquals("[D][✓] return book (by: 21 MARCH 2050 hours)", 
        new DeadlinesTask(true, "return book", endingTimeStub).toString());
    }


    @Test
    public void testToFileFormat() {
        LocalDateTime endingTimeStub = LocalDateTime.parse("2019-03-21T20:50:00");  

        assertEquals("D|1|return book|2019-03-21T20:50\n", 
        new DeadlinesTask(true, "return book", endingTimeStub).toFileFormat());
    }
}