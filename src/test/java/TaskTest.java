

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {

    @Test
    public void testToString() {
        assertEquals("\u2718" + " homework",new Task("homework").toString());
    }

    @Test
    public void testgetStatusIcon() {
        assertEquals("\u2718",new Task("homework").getStatusIcon());
    }

}