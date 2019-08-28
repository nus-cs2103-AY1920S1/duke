import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void testDateTimeFormat() {
        assertEquals("Aug 6 2pm", 
        new DateTime(14, 00, 6, "Aug" , 2019).toString());
    }
}