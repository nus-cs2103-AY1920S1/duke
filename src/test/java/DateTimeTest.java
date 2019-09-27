import trackr.date.DateTime;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void testGetTime() throws ParseException {
        DateTime dt = new DateTime("12/12/2019 1800");
        assertEquals("6:00pm", dt.getTime());
    }
}
