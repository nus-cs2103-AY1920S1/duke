import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    static protected final char UNICODE_TICK = (char) 0x2713;
    static protected final char UNICODE_CROSS = (char) 0x2718;

    @Test
    public void testStringConversion() {
        assertEquals("[T][" + UNICODE_CROSS + "] test", new ToDoTask("test").toString());
    }

    @Test
    public void testMarkUnmark() {
        ToDoTask toDoTask = new ToDoTask("test");
        assertEquals("[T][" + UNICODE_CROSS + "] test", toDoTask.toString());
        assertEquals("[T][" + UNICODE_TICK + "] test", toDoTask.getTaskMarkedAsDone().toString());
        assertEquals("[T][" + UNICODE_CROSS + "] test", toDoTask.getTaskMarkedAsDone().getTaskMarkedUndone().toString());
    }
}