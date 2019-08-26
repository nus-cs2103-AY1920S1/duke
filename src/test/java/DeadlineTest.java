import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void deadlineTest() {
        System.out.println("DeadlineTest starts");
        Deadline deadline = new Deadline("testing", "11/11/11 11:11");
        assertEquals("[D][N] testing (by: Fri Nov 11 11:11:00 SGT 2011)", deadline.toString());
        assertEquals("D | 0 | testing | 11/11/11 11:11", deadline.toWriteFile());
        System.out.println("DeadlineTest Pass");
    }
}
