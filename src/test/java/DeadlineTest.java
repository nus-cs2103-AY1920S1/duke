import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getSaveString_void_returnsCorrectlyFormattedString() {
        Deadline d = new Deadline("Some stuff to do", "25/11/2019 1100");
        assertEquals(d.getSaveString(), "D|0|Some stuff to do|25/11/2019 1100");
    }

    @Test
    public void toString_void_producesCorrectlyFormattedString() {
        Deadline d = new Deadline("Some stuff to do", "25/11/2019 1100");
        assertEquals(d.toString(), "[D][✘] Some stuff to do (by: 25 November 2019 11:00AM)");
    }
}

