package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testMarkAsDone() {
        assertEquals(true, new ToDo("test").markAsDone().isDone());
    }

    @Test
    public void testGetSummaryForDatabase() {
        assertEquals("T | 0 | bye", new ToDo("bye").getSummaryForDatabase());
        assertEquals("T | 1 | bye", new ToDo("bye").markAsDone().getSummaryForDatabase());
    }
}

