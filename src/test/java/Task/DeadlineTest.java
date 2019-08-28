package duke.test.Task;

import duke.helper.DateTimeHelper;
import duke.task.Deadline;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTypeTest_Success() throws DukeException {
        String d = "deadline return book /by 2019-09-17 0900";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Deadline deadL = new Deadline("return book", ldt);
        assertEquals("[D]", deadL.getType());
    }

    @Test
    public void deadlineGetDescription_Success() throws DukeException {
        String d = "deadline return book /by 2019-09-17 0900";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Deadline deadL = new Deadline("return book", ldt);
        assertEquals("return book|" + DateTimeHelper.formatOutput(ldt), deadL.getDescription());
    }

    @Test
    public void deadlineToString_Success() throws DukeException {
        String d = "deadline return book /by 2019-09-17 0900";
        String assertD = "[D]" + "[" + "\u2718" + "] return book (by: 2019-09-17 0900)";
        LocalDateTime ldt = DateTimeHelper.formatInput("2019-09-17 0900");
        Deadline deadL = new Deadline("return book", ldt);
        assertEquals(assertD, deadL.toString());
    }
}
