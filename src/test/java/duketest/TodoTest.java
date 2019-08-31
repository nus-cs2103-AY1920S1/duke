import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private static final String TICK = "✓";     // \u2713
    private static final String CROSS = "✗";    // \u2717

    @Test
    public void toStringTest() {
        Todo todo = new Todo("This is a Todo.");
        assertEquals("[T][" + CROSS + "] This is a Todo.", todo.toString());
        todo.setCompleted(true);    
        assertEquals("[T][" + TICK + "] This is a Todo.", todo.toString());
    }


}