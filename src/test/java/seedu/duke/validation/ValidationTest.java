package seedu.duke.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.validation.Validation;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.Time;
import seedu.duke.commons.exceptions.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    @Test
    public void dummyGetValidatedDate() throws DukeException {
        Assertions.assertEquals((new Date(13, 9, 2018)).toString(),
                Validation.getValidatedDate("13/09/2018").toString());
    }

    @Test
    public void dummyGetValidatedTime() throws DukeException {
        assertEquals((new Time(11, 55)).toString(),
                Validation.getValidatedTime("1155").toString());
    }
}
