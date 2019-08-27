import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDateTime(){
        assertEquals("10/10/2001 1000", new DateTime("10/10/2001 1000").toString());
    }

    @Test
    public void testUi(){
        assertEquals("hi", new Ui().printMessage("hi"));
    }

    @Test
    public void testDukeException(){
        assertEquals("hi", new DukeException("hi").getMessage());
    }
}
