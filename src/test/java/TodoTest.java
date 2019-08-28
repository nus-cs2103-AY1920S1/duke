import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToStringintxt() {
        Todo t = new Todo("dinner");
        String s = "T,0,dinner";
        assertEquals(s, t.toStringintxt());
    }

    @Test
    public void testToString() {
        Todo t = new Todo("read book");
        String s = "[T][\u2718] read book";
        assertEquals(s, t.toString());
    }



}
