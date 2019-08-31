import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the JUnit tests for ToDo class.
 */
public class TestToDo {

    /**
     * Tests the toString method of Todo class.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][" +  "0" + "] test",new ToDo("test").toString());
    }
}
