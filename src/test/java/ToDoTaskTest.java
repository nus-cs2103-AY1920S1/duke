import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToStringFormat() {
        assertEquals("[T][✓] join sports club", 
        new ToDoTask(true, "join sports club").toString());
    }


    @Test
    public void testToFileFormat() {
        assertEquals("T|1|join sports club\n", 
        new ToDoTask(true, "join sports club").toFileFormat());
    }
}