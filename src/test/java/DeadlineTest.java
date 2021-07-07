import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void getBy() throws ParseException {
        Deadline d = new Deadline("yoyo", "25/02/1998 1600");
        assertEquals("25/02/1998 1600", d.getBy());
    }
}
