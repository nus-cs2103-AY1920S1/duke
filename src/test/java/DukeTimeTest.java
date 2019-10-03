import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import task.DukeTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTimeTest {

    @Test
    public void processTime_success() throws InvalidInputException {
        assertEquals("03:00PM", DukeTime.processTime("1500").toString());
        assertEquals("03:00AM", DukeTime.processTime("0300").toString());
    }

    @Test
    public void processTime_throwsException() {
        assertThrows(InvalidInputException.class, () -> DukeTime.processTime("300"));
        assertThrows(InvalidInputException.class, () -> DukeTime.processTime("2500"));
        assertThrows(InvalidInputException.class, () -> DukeTime.processTime("1290"));
        assertThrows(InvalidInputException.class, () -> DukeTime.processTime("2590"));
    }
}
