import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DukeTest {
    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

}