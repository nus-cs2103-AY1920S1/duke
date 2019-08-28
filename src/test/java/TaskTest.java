import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void toString_normalTask_writtenCorrectly() {
        Task t = new Task("Do the dishes");
        assertEquals("[" + "\u2718" + "] " + "Do the dishes", t.toString());
    }
}
