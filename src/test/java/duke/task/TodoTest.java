package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] run", new Todo("run").toString());
    }

    @Test
    public void markAsDone_regularTask_success() {
        Task task = new Todo("run");
        task.markAsDone();
        assertEquals("[T][✓] run", task.toString());
    }
}
