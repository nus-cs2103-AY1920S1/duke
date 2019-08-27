
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask {
    @Test
    public void testStringConversion(){
        assertEquals("[" + "\u2718" + "] test",new Task("test").toString());
    }

    @Test
    public void testMarkAsDone(){
        Task t = new Task("test");
        t.markAsDone();
        assertEquals(true, t.isDone);
    }

}
