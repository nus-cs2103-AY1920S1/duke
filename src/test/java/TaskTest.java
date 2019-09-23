import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Deadline;
import task.Todo;
import task.Event;
import utils.Parser;
import error.DukeException;

public class TaskTest {

    @Test
    public void testTodo_toString() {
        //test 1
        assertEquals("[T][ ] hello", new Todo("hello").toString());
        //test 2
        Task todo = new Todo("hello");
        todo.markAsDone();
        assertEquals("[T][X] hello", todo.toString());
    }

    @Test
    public void testEvent_toString() {
        //test 1
        assertEquals("[E][ ] return book (at: 2/12/2019 1800)", new Event("return book", "2/12/2019 1800").toString());
        //test 2
        Task event = new Event("return book", "2/12/2019 1800");
        event.markAsDone();
        assertEquals("[E][X] return book (at: 2/12/2019 1800)", event.toString());
    }

    @Test
    public void testDeadline_toString() {
        //test 1
        assertEquals("[D][ ] return book (by: 2/12/2019 1800)",
                new Deadline("return book", "2/12/2019 1800").toString());
        //test 2
        Task deadline = new Deadline("return book", "2/12/2019 1800");
        deadline.markAsDone();
        assertEquals("[D][X] return book (by: 2/12/2019 1800)", deadline.toString());
    }

}
