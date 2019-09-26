package utils;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringToDateTest {

    @Test
    void constructor_invalidDateFormat_expectedException() {
        assertThrows(DukeException.class, () -> {
            new StringToDate("10-10");
        });
    }

    @Test
    void toString_string_formattedDateOutput() throws DukeException {
        assertEquals("10-10-2019 18:00", new StringToDate("10-10-2019 18:00").toString());
    }

}
