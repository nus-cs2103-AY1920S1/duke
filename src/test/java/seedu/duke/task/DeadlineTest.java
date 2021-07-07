package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;
import seedu.duke.model.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private Deadline dummyDeadline = new Deadline("dummyString",
            new DateAndTime(new Date(1, 3, 2014)));
    private Deadline dummyDeadline2 = new Deadline("dummyString",
            new DateAndTime(new Date(2, 4, 2024), new Time(14, 4)), true);

    @Test
    public void dummyToString() {
        assertEquals("[D][ ] dummyString (by: 01/03/2014)", dummyDeadline.toString());
    }

    @Test
    public void dummyToString2() {
        assertEquals("[D][+] dummyString (by: 02/04/2024 1404)", dummyDeadline2.toString());
    }

}
