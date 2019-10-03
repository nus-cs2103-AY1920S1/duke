package duke.util;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateTimeTest {
    private DateTime dateTime;

    @Test
    public void parseString_invalidDateFormat_raiseException() {
        Assertions.assertThrows(DukeException.class, () -> {
            DateTime.parseString("Not a valid date.");
        });
    }

    @Test
    public void parseString_compliantDateFormat_noException() throws DukeException {
        this.dateTime = DateTime.parseString("11/11/3019 0000");
    }

    @Test
    public void toString_methodCall_formattedOutput() throws DukeException {
        this.dateTime = DateTime.parseString("11/11/3019 0000");
        assertEquals("11 Nov 3019, 12:00 AM",
                this.dateTime.toString());
    }
}
