package duke.task;

import duke.command.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTaskTest {
    @Test
    void deadlineTaskConstructor_invalidDescriptionCallsValidate_throwsDukeInvalidArgumentException() {
        assertThrows(DukeInvalidArgumentException.class,
                () -> new DeadlineTask(
                        TaskTestConstants.INVALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_DATE_1));
    }

    @Test
    void deadlineTaskConstructor_invalidDatetimeCallsValidate_throwsDukeInvalidArgumentException() {
        assertAll("Invalid date times",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new DeadlineTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_DATE_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new DeadlineTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_DATE_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new DeadlineTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_DATE_3)));
    }

    @Test
    void deadlineTaskConstructor_validDatetime_success() {
        assertAll("Valid date times",
                () -> new DeadlineTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_DATE_1),
                () -> new DeadlineTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_DATE_2),
                () -> new DeadlineTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_DATE_3),
                () -> new DeadlineTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_DATE_4));
    }

    @Test
    void getTaskType() {
        assertDoesNotThrow(() -> {
            DeadlineTask deadline = new DeadlineTask(
                    TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_DATE_2);
            assertEquals(TaskType.DEADLINE, deadline.getTaskType());
        });
    }

    @Test
    void getTiming() {
        assertDoesNotThrow(() -> {
            DeadlineTask deadline = new DeadlineTask(
                    TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_DATE_3);
            assertEquals(
                    TaskTestConstants.VALID_DATE_3,
                    deadline.getTiming());
        });
    }

    @Test
    void getDescription() {
        assertDoesNotThrow(() -> {
            DeadlineTask deadline = new DeadlineTask(
                    TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_DATE_4);
            assertEquals(TaskTestConstants.VALID_DESCRIPTION_1, deadline.getDescription());
        });
    }

    @Test
    void doneStatusDependentTest() {
        //tests isDone, getStatusIcon, getStatusText, and setDone

        assertDoesNotThrow(() -> {
            DeadlineTask deadline = new DeadlineTask(
                    TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_DATE_1);

            assertFalse(deadline.isDone());
            assertEquals("\u2718", deadline.getStatusIcon());
            assertEquals(
                    String.format("[D][\u2718] %s (by: %s)",
                            TaskTestConstants.VALID_DESCRIPTION_1,
                            TaskUtil.getDisplayTime(
                                    TaskUtil.getDateFromString(
                                            TaskTestConstants.VALID_DATE_1))),
                    deadline.getStatusText());

            deadline.setDone(true);

            assertTrue(deadline.isDone());
            assertEquals("\u2713", deadline.getStatusIcon());
            assertEquals(
                    String.format("[D][\u2713] %s (by: %s)",
                            TaskTestConstants.VALID_DESCRIPTION_1,
                            TaskUtil.getDisplayTime(
                                    TaskUtil.getDateFromString(
                                            TaskTestConstants.VALID_DATE_1))),
                    deadline.getStatusText());
        });
    }
}
