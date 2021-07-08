package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private static final String DESCRIPTION = "run";

    @Test
    public void stringConversion() {
        assertEquals(String.format("[T][✘] %s", DESCRIPTION),
                new Todo(DESCRIPTION).toString());
    }

    @Test
    public void isDone() {
        assertEquals(false, new Todo(DESCRIPTION).isDone());
        assertEquals(true, new Todo(DESCRIPTION, true).isDone());
    }

    @Test
    public void getDescription() {
        assertEquals(DESCRIPTION, new Todo(DESCRIPTION).getDescription());
    }

    @Test
    public void markAsDone() {
        Task task = new Todo(DESCRIPTION);
        task.markAsDone();
        assertEquals(true, task.isDone());
    }
}
