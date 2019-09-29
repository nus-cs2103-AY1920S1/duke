import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing Task class.
 */
public class TaskTest {
    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    /**
     * Tests setDone method of Task class.
     */
    @Test
    public void setDoneTest() {
        Task t = new Task("buy book");
        t.setDone();
        assertEquals("1", t.getIsDone());
    }

    /**
     * Tests getDescription method of Task class.
     */
    @Test
    public void getDescriptionTest() {
        Task t = new Task("buy book");
        assertEquals("buy book", t.getDescription());
    }

    /**
     * Tests getType method of Task class.
     */
    @Test
    public void getTypeTest() {
        Todo t  = new Todo("buy book");
        assertEquals("T", t.getType());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Event e = new Event("buy book", LocalDateTime.parse("12/09/2019 1800", dtf));
        assertEquals("E", e.getType());
        Deadline d = new Deadline("buy book", LocalDateTime.parse("12/09/2019 1800", dtf));
        assertEquals("D", d.getType());
    }
}