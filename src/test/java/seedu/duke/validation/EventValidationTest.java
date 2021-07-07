package seedu.duke.validation;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.validation.EventValidation;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.commons.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventValidationTest {

    @Test
    public void dummyGetValidatedDescriptionAndDateTimeString() throws DukeException {
        String[] arr = EventValidation.getValidatedDescriptionAndDateTime("dummyTaskString /at "
                + "03/01/2017 1400-1600");
        assertEquals("dummyTaskString", arr[0]);
        assertEquals("03/01/2017 1400-1600", arr[1]);
    }

    public void dummyGetValidatedDateAndTime() throws DukeException {
        DateAndTime dummyDateAndTime = EventValidation.getValidatedDateAndTime("11/14/2011 0905-0745");
        assertEquals("11/14/2011 0905-0745", dummyDateAndTime.toString());
    }
}
