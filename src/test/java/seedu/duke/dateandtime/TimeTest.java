package seedu.duke.dateandtime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void dummyToString() {
        Assertions.assertEquals("2359", new Time(23, 59).toString());
    }

}
