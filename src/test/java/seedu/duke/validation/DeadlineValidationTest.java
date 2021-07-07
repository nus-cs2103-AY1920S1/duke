package seedu.duke.validation;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.validation.DeadlineValidation;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.commons.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineValidationTest {

    @Test
    public void dummyGetValidatedDescriptionAndDateTimeString() throws DukeException {
        String[] arr = DeadlineValidation.getValidatedDescriptionAndDateTime("dummyTaskString /by 01/03/2010");
        assertEquals("dummyTaskString", arr[0]);
        assertEquals("01/03/2010", arr[1]);
    }

    public void dummyGetValidatedDateAndTime() throws DukeException {
        DateAndTime dummyDateAndTime = DeadlineValidation.getValidatedDateAndTime("13/12/2019 0807");
        assertEquals("13/12/2019 0807", dummyDateAndTime.toString());
    }
}
