import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void testConvertDate() {
        DateAndTime d = new DateAndTime(" 18/09/2020 1800");
        assertEquals("18 September 2020", d.convertDate());
    }

    @Test
    public void testConvertTime() {
        DateAndTime d = new DateAndTime(" 18/09/2020 1800");
        assertEquals("6.00p.m.", d.convertTime());
    }
}
