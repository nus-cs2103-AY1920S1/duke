package seedu.duke.dateandtime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.model.dateandtime.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    @Test
    public void dummyGetMonth() {
        Assertions.assertEquals("February", Date.getMonth(2));
    }

    @Test
    public void dummyToString() {
        assertEquals("29/02/2019", new Date(29, 2, 2019).toString());
    }

}
