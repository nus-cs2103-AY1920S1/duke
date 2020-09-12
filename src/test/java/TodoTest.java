import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test Todo class.
 */
public class TodoTest {

    /**
     * Test method to test toString()
     */
    @Test
    public void testToString() {
        Todo t = new Todo("eat lunch");
        String expected = "[T][\u2718] eat lunch";
        assertEquals(expected, t.toString());
    }
    /**
     * Test method to test toTextFileString()
     */
    @Test
    public void testToTextFileString() {
        Todo t = new Todo("exercise");
        String expected = "T|0|exercise";
        assertEquals(expected, t.toTextFileString());
    }

}
