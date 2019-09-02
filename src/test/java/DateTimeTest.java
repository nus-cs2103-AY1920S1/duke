import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void testConvertDate() {
        DateTime d = new DateTime(" 18/09/2020 1800");
        assertEquals("18 September 2020", d.convertDate());
    }

    @Test
    public void testConvertTime() {
        DateTime d = new DateTime(" 18/09/2020 1800");
        assertEquals("6.00p.m.", d.convertTime());
    }
}
