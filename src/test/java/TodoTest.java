import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        Todo t = new Todo("eat lunch");
        String expected = "[T][\u2718] eat lunch";
        assertEquals(expected, t.toString());
    }

    @Test
    public void testToTextFileString() {
        Todo t = new Todo("exercise");
        String expected = "T|0|exercise";
        assertEquals(expected, t.toTextFileString());
    }

}
