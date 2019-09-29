package duke.task;

import duke.command.DukeInvalidArgumentException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TaskUtilTest {

    @Test
    void getDateFromString() {
        assertAll("Valid",
                () -> TaskUtil.getDateFromString(TaskTestConstants.VALID_DATE_1),
                () -> TaskUtil.getDateFromString(TaskTestConstants.VALID_DATE_2),
                () -> TaskUtil.getDateFromString(TaskTestConstants.VALID_DATE_3),
                () -> TaskUtil.getDateFromString(TaskTestConstants.VALID_DATE_4));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(TaskTestConstants.INVALID_DATE_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(TaskTestConstants.INVALID_DATE_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getDateFromString(TaskTestConstants.INVALID_DATE_3)));
    }

    @Test
    void getTimeFromString() {
        assertAll("Valid",
                () -> TaskUtil.getTimeFromString(TaskTestConstants.VALID_TIME_1),
                () -> TaskUtil.getTimeFromString(TaskTestConstants.VALID_TIME_2));

        assertAll("Invalid",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(TaskTestConstants.INVALID_TIME_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(TaskTestConstants.INVALID_TIME_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> TaskUtil.getTimeFromString(TaskTestConstants.INVALID_TIME_3)));
    }

    @Test
    void validateTaskDescription() {
        assertThrows(DukeInvalidArgumentException.class,
                () -> TaskUtil.validateTaskDescription(TaskTestConstants.INVALID_DESCRIPTION_1));
    }
}
