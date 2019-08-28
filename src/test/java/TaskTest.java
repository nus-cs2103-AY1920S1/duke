import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void nameTest() {
        Task temp = new Task("hello", false);
        assertEquals("hello", temp.getName());
    }
}





