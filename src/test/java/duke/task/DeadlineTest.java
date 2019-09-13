package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    public void createDeadlineTest1() {
        Deadline d = new Deadline("iP", "2:00pm 12 Sep 2019");
        assertEquals(d.toString(), "[D][X] iP (by: 2:00pm 12 Sep 2019)");
    }

    @Test
    public void createDeadlineTest2() {
        Deadline d = new Deadline("iP", "2:00pm 12 Sep 2019", true);
        assertEquals(d.toString(), "[D][O] iP (by: 2:00pm 12 Sep 2019)");
    }

    @Test
    public void getDateTest() {
        Deadline d = new Deadline("iP", "2:00pm 12 Sep 2019", true);
        assertEquals(d.getDate(), "2:00pm 12 Sep 2019");
    }
}