package utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringToDateTest {

    @Test
    void constructor_invalidDateFormat_expectedException() {
        assertThrows(ParseException.class, () -> {
            new StringToDate("10-10");
        });
    }

    @Test
    void toString_string_formattedDateOutput() throws ParseException {
        assertEquals("10-10-2019 18:00", new StringToDate("10-10-2019 18:00").toString());
    }

}
