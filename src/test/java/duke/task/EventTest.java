package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    public void createEventTest1() {
        Event e = new Event("lecture", "2:00pm 12 Sep 2019");
        assertEquals(e.toString(), "[E][X] lecture (at: 2:00pm 12 Sep 2019)");
    }

    @Test
    public void createEventTest2() {
        Event e = new Event("lecture", "2:00pm 12 Sep 2019", true);
        assertEquals(e.toString(), "[E][O] lecture (at: 2:00pm 12 Sep 2019)");
    }

    @Test
    public void getDateTest() {
        Event e = new Event("lecture", "2:00pm 12 Sep 2019", true);
        assertEquals(e.getDate(), "2:00pm 12 Sep 2019");
    }
}
