import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline t = new Deadline("Read Book","2/2/2012 1900");
        String s = "[D][\u2718] Read Book (by: 2/2/2012 1900)";
        assertEquals(s, t.toString());
    }


}