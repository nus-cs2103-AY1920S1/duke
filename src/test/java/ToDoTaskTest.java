import duke.tasks.ToDoTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * A test used to test the todo task class.
 */
public class ToDoTaskTest {
    
    /**
     * Tests the ToStringFormat of the todotask class. This ensures that the task is output 
     * per the specified formats.
     */
    @Test
    public void testToStringFormat() {
        assertEquals("[T][✓] join sports club", 
        new ToDoTask(true, "join sports club").toString());
    }

    /**
     *  Tests the toFileFormat method of the todotask class. This ensures that the task is
     *  saved in a proper format when being stored.
     * 
     */
    @Test
    public void testToFileFormat() {
        assertEquals("T|1|join sports club\n", 
        new ToDoTask(true, "join sports club").toFileFormat());
    }
}