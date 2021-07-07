import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {
    @Test
    public void testStringConversion() {

        assertEquals("[T][\u2718] test todo", (new ToDo("test todo")).toString());
    }


}
