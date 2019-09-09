import duke.task.DeadlineTask;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void toString_markAsDone_success() {
        Task testTask = new DeadlineTask("This is a test task.",
                LocalDateTime.of(1999, 1, 20, 01, 00));
        assertEquals("[D][-] This is a test task. (by: 20 of JANUARY, 1999, at 0100 hrs)", testTask.toString());
        testTask.markAsDone();
        assertEquals("[D][+] This is a test task. (by: 20 of JANUARY, 1999, at 0100 hrs)", testTask.toString());
    }
    
    @Test
    public void formattedString_markAsDone_success() {
        Task testTask = new DeadlineTask("This is a test task.",
                LocalDateTime.of(100, 11, 30, 12, 59));
        assertEquals("D | - | This is a test task. | 30/11/0100 1259\n", testTask.formattedString());
        testTask.markAsDone();
        assertEquals("D | + | This is a test task. | 30/11/0100 1259\n", testTask.formattedString());
    }
}