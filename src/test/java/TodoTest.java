import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        Todo t = new Todo("test");
        assertEquals("[T][✘] test", t.toString());
    }
}
