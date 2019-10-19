package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeParserTest {

    @Test
    void parseDate() {
        LocalDate date = DateTimeParser.parseDate("01/01/2019");
        assertEquals(LocalDate.of(2019, 1, 1), date);
    }

    @Test
    void parseTime_Am() {
        LocalTime time = DateTimeParser.parseTime("0700");
        assertEquals(LocalTime.of(7, 0), time);
    }

    @Test
    void parseTime_Pm() {
        LocalTime time = DateTimeParser.parseTime("1800");
        assertEquals(LocalTime.of(18, 0), time);
    }
}
