import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTaskGetDescription() {
        Task task = new Task("complete hw");
        assertEquals("complete hw", task.getDescription());
    }

    @Test
    public void  testTodoGetIsDone() {
        Todo td = new Todo("assignments");
        assertEquals(false, td.getIsDone());
    }

    @Test
    public void testDeadlineGetBy() {
        Deadline dd = new Deadline("splashdown poster", new Date(1000000000));
        assertEquals(new Date(1000000000), dd.getBy());
    }

}
