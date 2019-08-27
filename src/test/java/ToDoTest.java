import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void check_undoneTask_toString() {
        Task task1 = new ToDo("Do Project");
        assertEquals("[T][\u2718] Do Project", task1.toString());
    }

    @Test
    public void check_doneTask_toString() {
        Task task2 = new ToDo("Do Project Completed");
        task2.markAsDone();
        assertEquals("[T][\u2713] Do Project Completed", task2.toString());
    }
}
