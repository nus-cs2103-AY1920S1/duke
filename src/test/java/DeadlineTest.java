import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void toStringDeadlineTest() {
        assertEquals("[D][-] CS2103 (by: 2019-12-02T18:00)",
                new Deadline("CS2103", "02/12/2019 1800").toString());
    }

    @Test
    void toDataStringDeadlineTest() {
        assertEquals("D | 0 | CS2103 | 02/12/2019 1800",
                new Deadline("CS2103", "02/12/2019 1800").toDataString());
    }

}
