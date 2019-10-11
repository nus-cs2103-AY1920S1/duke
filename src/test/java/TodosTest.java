/**
 * Test class to test the print() and toString() method in Todos class.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testPrint() {
        assertEquals("T @ 1 @ borrow book", new Todos("borrow book", true).print());
    }

    @Test
    public void testToString() {
        assertEquals("[T][\u2713] play basketball", new Todos("play basketball", true).toString());
    }
}