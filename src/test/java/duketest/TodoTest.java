import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private static final String CIRCLE = "O";     
    private static final String CROSS = "X";    

    @Test
    public void toStringTest() {
        Todo todo = new Todo("This is a Todo.");
        assertEquals("[T][" + CROSS + "] This is a Todo.", todo.toString());
        todo.setCompleted(true);    
        assertEquals("[T][" + CIRCLE + "] This is a Todo.", todo.toString());
    }


}