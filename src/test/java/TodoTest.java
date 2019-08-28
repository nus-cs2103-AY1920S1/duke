import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][\u2718] borrow book", new Todo("borrow book").toString());
    }
}