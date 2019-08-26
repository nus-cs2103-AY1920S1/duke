package duke.task;

import duke.command.DukeInvalidArgumentException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TaskUtilTest {
    private String VALID_DATE_1 = "01/01/1997 0000";
    private String VALID_DATE_2 = " 01/01/1997 0003   ";
    private String INVALID_DATE_1 = "1/Jan/1997 1200";
    private String INVALID_DATE_2 = "";
    private String INVALID_DATE_3 = "01/01/1997      1200";

    private String VALID_TIME_1 = "0000";
    private String VALID_TIME_2 = "  0120  ";
    private String INVALID_TIME_1 = "01 20";
    private String INVALID_TIME_2 = "130am";
    private String INVALID_TIME_3 = VALID_DATE_1;

    private String INVALID_DESCRIPTION_1 = "";

    @Test
    void getDateFromString() throws DukeInvalidArgumentException {
        assertAll("Valid",
                () -> TaskUtil.getDateFromString(VALID_DATE_1),
                () -> TaskUtil.getDateFromString(VALID_DATE_2));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(INVALID_DATE_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(INVALID_DATE_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(INVALID_DATE_3)));
    }

    @Test
    void getTimeFromString() throws DukeInvalidArgumentException {
        assertAll("Valid",
                () -> TaskUtil.getTimeFromString(VALID_TIME_1),
                () -> TaskUtil.getTimeFromString(VALID_TIME_2));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(INVALID_TIME_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(INVALID_TIME_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(INVALID_TIME_3)));
    }

    @Test
    void validateTaskDescription() throws DukeInvalidArgumentException {
        assertThrows(DukeInvalidArgumentException.class,
                () -> TaskUtil.validateTaskDescription(INVALID_DESCRIPTION_1));
    }
}
