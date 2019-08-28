import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testGetStatusIcon() {
        Task t = new Task("read book");
        String expected = "\u2718";
        assertEquals(expected, t.getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        Task t = new Task("cook dinner");
        String expected = "cook dinner";
        assertEquals(expected, t.getDescription());
    }

    @Test
    public void testMarkAsDone() {
        Task t = new Task("clean room");
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
