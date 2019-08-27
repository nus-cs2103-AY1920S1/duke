import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] sleep", new ToDo("sleep").toString());
        assertEquals("[T][✘] run at the gym", new ToDo("run at the gym").toString());
    }
}
