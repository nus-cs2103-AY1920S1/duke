import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printCorrectly() {
        Task todo = new Todo("borrow book", false);
        assertEquals("[T][-] borrow book", todo.toString());
    }
}