import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyFailTest() {
        assertEquals("lalala", "placeholder");
    }
    @Test
    public void dummyPassTest() {
        assertEquals("lalala", "lalala");
    }
}
