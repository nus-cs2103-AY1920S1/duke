import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    protected static final char UNICODE_TICK = 'O';
    protected static final char UNICODE_CROSS = 'X';

    @Test
    public void testStringConversion() {
        assertEquals("[T][" + UNICODE_CROSS + "] test", new ToDoTask("test").toString());
    }

    @Test
    public void testMarkUnmark() {
        ToDoTask toDoTask = new ToDoTask("test");
        assertEquals("[T][" + UNICODE_CROSS + "] test", toDoTask.toString());
        assertEquals("[T][" + UNICODE_TICK + "] test", toDoTask.getTaskMarkedAsDone().toString());
        assertEquals("[T][" + UNICODE_CROSS + "] test", 
                     toDoTask.getTaskMarkedAsDone().getTaskMarkedUndone().toString());
    }
}