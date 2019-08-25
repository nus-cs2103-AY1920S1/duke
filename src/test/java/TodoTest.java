import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    void getDescription() {
        Todo t = new Todo("yoyo watermelon");
        assertEquals("yoyo watermelon", t.getDescription());
    }
}
