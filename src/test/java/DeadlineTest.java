import exceptions.DukeException;
import task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void printToOutputTest() throws DukeException {
        assertEquals("D | 0 | submission  | 04/07/2019 2359" ,
                new Deadline("submission /by 4/7/2019 2359").printToOutput());
    }
}
