import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTest {

    @Test
    void testToString1() {
        Date day = new Date("2/10/2020 1000");
        assertEquals("2nd of October 2020, 10am", day.toString());
    }

    @Test
    void testToString2() {
        Date day = new Date("2/10/2020 0000");
        assertEquals("2nd of October 2020, 12am", day.toString());
    }

    @Test
    void testToString3() {
        Date day = new Date("2/10/2020 0500");
        assertEquals("2nd of October 2020, 5am", day.toString());
    }
}