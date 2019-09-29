import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    public void deadlineTest() {
        Task deadline = new Deadline("Deadline deadlinetest", "23/05/1997", "1800");
        assertEquals("Deadline deadlinetest", deadline.getDescription());
        assertEquals("[D][x] Deadline deadlinetest (by: 23 May 1997, 6:00 PM)", deadline.toString());
        assertEquals("D | 0 | Deadline deadlinetest | 23/05/1997 1800", deadline.fileFormat());
        assertEquals("[x]", deadline.getStatusIcon());

        deadline.markAsDone();

        assertEquals("D | 1 | Deadline deadlinetest | 23/05/1997 1800", deadline.fileFormat());
        assertEquals("[done]", deadline.getStatusIcon());
        assertEquals("[D][done] Deadline deadlinetest (by: 23 May 1997, 6:00 PM)", deadline.toString());
    }
}