import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import task.DukeDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeDateTest {

    @Test
    public void processDate_success() throws InvalidInputException {
        assertEquals("Sun, 22 Sep 2019", DukeDate.processDate("22/9/2019").toString());
        assertEquals("Sun, 22 Sep 2019", DukeDate.processDate("22/09/2019").toString());
    }

    @Test
    public void processDate_throwsException() {
        assertThrows(InvalidInputException.class, () -> DukeDate.processDate("22 Sep 19"));
        assertThrows(InvalidInputException.class, () -> DukeDate.processDate("Sunday"));
        assertThrows(InvalidInputException.class, () -> DukeDate.processDate("22/9"));
        assertThrows(InvalidInputException.class, () -> DukeDate.processDate("41/27/2019"));
    }

}
