import org.junit.jupiter.api.Test;
import tool.DateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void testDateTime() {
        DateTime dt = new DateTime("18/10/1999 1800");
        String res = dt.toString();
        assertEquals("18 October 1999 1800hrs", res);
    }
}

