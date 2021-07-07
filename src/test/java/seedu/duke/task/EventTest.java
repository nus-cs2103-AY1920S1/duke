package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;
import seedu.duke.model.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event dummyEvent = new Event("dummyString",
            new DateAndTime(new Date(7, 5, 2031), new Time(12, 32),
                    new Time(15, 13)));
    private Event dummyEvent2 = new Event("dummyString",
            new DateAndTime(new Date(3, 12, 2064),
                    new Time(12, 34), new Time(23, 54)), true);

    @Test
    public void dummyToString() {
        assertEquals("[E][ ] dummyString (at: 07/05/2031 1232-1513)", dummyEvent.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[E][+] dummyString (at: 03/12/2064 1234-2354)", dummyEvent2.toString());
    }

}
