import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void testTodo() {
        Task todo = new Todo("hello");
        String expected = "[T][ ] hello";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testEvent() {
        Task event = new Event("return book", "2/12/2019 1800");
        String expected = "[E][ ] return book (at: 2/12/2019 1800)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testDeadline() {
        Task deadline = new Deadline("return book", "2/12/2019 1800");
        String expected = "[D][ ] return book (by: 2/12/2019 1800)";
        assertEquals(expected, deadline.toString());
    }
}
