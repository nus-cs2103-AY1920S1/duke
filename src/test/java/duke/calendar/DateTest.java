package duke.calendar;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DateTest {
    @Test
    public void processedDate_success() throws DukeException {
        Date date = new Date("2/12/2019");
        assertEquals("2nd of DECEMBER 2019", date.toString());
    }

    @Test
    public void processedDate_wrongMonth_exceptionThrown() throws DukeException {
        try {
            Date date = new Date("2/13/2019");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please input a valid month.", e.getMessage());
        }
    }

    @Test
    public void processedDate_wrongDay_exceptionThrown() throws DukeException {
        try {
            Date date = new Date("29/2/2019");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please input a valid day.", e.getMessage());
        }
    }

    @Test
    public void processedDate_wrongFormat_exceptionThrown() throws DukeException {
        try {
            Date date = new Date("2/2019");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Please specify the date" +
            " in the format date/month/year e.g. 2/12/2019.", e.getMessage());
        }
    }

}
