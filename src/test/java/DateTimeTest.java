import duke.date.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void testGetTime() {
        DateTime dt = new DateTime("12/12/2019 1800");
        assertEquals("6pm", dt.getTime());
    }
}
