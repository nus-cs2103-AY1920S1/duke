import static org.junit.jupiter.api.Assertions.assertEquals;
import task.Task;
import task.ToDos;
import org.junit.jupiter.api.Test;

class TodosTest {

    @Test
    public void toStringConversion_incompleteTask_taskStringReturned() {
        Task task = new ToDos("Say hello to me.");
        assertEquals("[T][✗] Say hello to me.", task.toString());
    }

    @Test
    public void toStringConversionForFile_incompleteTask_taskStringReturned() {
        Task task = new ToDos("Say hello to me.");
        assertEquals("T | 0 | Say hello to me.", task.toStringForFile());
    }
}