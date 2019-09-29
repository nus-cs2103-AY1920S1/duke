package duke.memo.task;

import duke.memo.record.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void deadlineTest() throws Throwable {
        Deadline deadline = new Deadline("return book /by 2/9/2019 2359");
        assertEquals("[D]✘ return book (by: 02/09/2019 2359)", deadline.toString());
        deadline.taskDone();
        assertEquals("[D]✓ return book (by: 02/09/2019 2359)", deadline.toString());
    }

    @Test
    void deadlineGetPrintableMsgTest() throws Throwable {
        Deadline deadline = new Deadline("return book /by 2/9/2019 2359");
        assertEquals("D | 0 | return book | 02/09/2019 2359", deadline.getPrintableMsg());
        deadline.taskDone();
        assertEquals("D | 1 | return book | 02/09/2019 2359", deadline.getPrintableMsg());
    }

}