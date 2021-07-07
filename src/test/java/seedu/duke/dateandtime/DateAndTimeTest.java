package seedu.duke.dateandtime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;

public class DateAndTimeTest {

    private Date date = new Date(1, 4, 2018);
    private Time time = new Time(17, 42);

    @Test
    public void dummyToString() {
        Assertions.assertEquals("01/04/2018 1742", new DateAndTime(date, time).toString());
    }

}
