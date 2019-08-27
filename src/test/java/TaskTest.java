import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[✘] sleep", new Task("sleep").toString());
        assertEquals("[✘] run at the gym", new Task("run at the gym").toString());
    }

    @Test
    public void markAsDone_null_success() throws Exception {
        Task task = new Task("sleep");
        task.markAsDone();
        assertEquals("[✓] sleep", task.toString());
    }

    @Test
    public void getStatusIcon_null_success() throws Exception {
        Task task = new Task("sleep");
        assertEquals("✘", task.getStatusIcon());
        task.markAsDone();
        assertEquals("✓", task.getStatusIcon());
    }
}
