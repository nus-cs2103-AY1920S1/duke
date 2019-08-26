package duke.task;

import duke.command.DukeInvalidArgumentException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TaskUtilTest {
    private String validDate1 = "01/01/1997 0000";
    private String validDate2 = " 01/01/1997 0003   ";
    private String invalidDate1 = "1/Jan/1997 1200";
    private String invalidDate2 = "";
    private String invalidDate3 = "01/01/1997      1200";

    private String validTime1 = "0000";
    private String validTime2 = "  0120  ";
    private String invalidTime1 = "01 20";
    private String invalidTime2 = "130am";
    private String invalidTime3 = validDate1;

    private String invalidDescription1 = "";

    @Test
    void getDateFromString() throws DukeInvalidArgumentException {
        assertAll("Valid",
                () -> TaskUtil.getDateFromString(validDate1),
                () -> TaskUtil.getDateFromString(validDate2));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(invalidDate1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(invalidDate2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(invalidDate3)));
    }

    @Test
    void getTimeFromString() throws DukeInvalidArgumentException {
        assertAll("Valid",
                () -> TaskUtil.getTimeFromString(validTime1),
                () -> TaskUtil.getTimeFromString(validTime2));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(invalidTime1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(invalidTime2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(invalidTime3)));
    }

    @Test
    void validateTaskDescription() throws DukeInvalidArgumentException {
        assertThrows(DukeInvalidArgumentException.class,
                () -> TaskUtil.validateTaskDescription(invalidDescription1));
    }
}
