import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void descriptionTest() {

        assertEquals("DESCRIPTION", new Todo("DESCRIPTION").description);
        assertEquals("DESCRIPTION", new Deadline("DESCRIPTION", "DUMMY").description);
        assertEquals("DESCRIPTION", new Event("DESCRIPTION", "DUMMY").description);

    }

    @Test
    public void isDoneTest() {

        assertEquals(false, new Deadline("DUMMY", "DUMMY").isDone);

        Deadline deadline = new Deadline("DUMMY", "DUMMY");
        deadline.markAsDone();

        assertEquals(true, deadline.isDone);
    }

    @Test
    public void dateTest() {
        assertEquals("DATE", new Deadline("DUMMY", "DATE").by);
        assertEquals("DATE", new Event("DUMMY", "DATE").at);
    }

    @Test
    public void taskObjectTest() {
        assertEquals("[T][\u2718] DESCRIPTION", new Todo("DESCRIPTION").toString());
        assertEquals("[D][\u2718] DESCRIPTION (by: DATE)", new Deadline("DESCRIPTION", "DATE").toString());
        assertEquals("[E][\u2718] DESCRIPTION (at: DATE)", new Event("DESCRIPTION", "DATE").toString());
    }

}
