package duke.task;

import duke.command.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTaskTest {
    @Test
    void eventTaskConstructor_invalidDescriptionCallsValidate_throwsDukeInvalidArgumentException() {
        assertThrows(DukeInvalidArgumentException.class,
                () -> new EventTask(
                        TaskTestConstants.INVALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_PERIOD_1));
    }

    @Test
    void eventTaskConstructor_invalidPeriodCallsValidate_throwsDukeInvalidArgumentException() {
        assertAll("Invalid periods",
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new EventTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_PERIOD_1)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new EventTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_PERIOD_2)),
                () -> assertThrows(
                        DukeInvalidArgumentException.class,
                        () -> new EventTask(
                                TaskTestConstants.VALID_DESCRIPTION_1,
                                TaskTestConstants.INVALID_PERIOD_3)));
    }

    @Test
    void eventTaskConstructor_validPeriods_success() {
        assertAll("Valid periods",
                () -> new EventTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_PERIOD_1),
                () -> new EventTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_PERIOD_2),
                () -> new EventTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_PERIOD_3),
                () -> new EventTask(
                        TaskTestConstants.VALID_DESCRIPTION_1,
                        TaskTestConstants.VALID_PERIOD_4));
    }

    @Test
    void getTaskType() {
        assertDoesNotThrow(() -> {
            EventTask event = new EventTask(TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_PERIOD_2);
            assertEquals(TaskType.EVENT, event.getTaskType());
        });
    }

    @Test
    void getTiming() {
        assertDoesNotThrow(() -> {
            EventTask event = new EventTask(TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_PERIOD_3);
            assertEquals(
                    TaskTestConstants.VALID_PERIOD_3,
                    event.getTiming());
        });
    }

    @Test
    void getDescription() {
        assertDoesNotThrow(() -> {
            EventTask event = new EventTask(TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_PERIOD_4);
            assertEquals(TaskTestConstants.VALID_DESCRIPTION_1, event.getDescription());
        });
    }

    @Test
    void doneStatusDependentTest() {
        //tests isDone, getStatusIcon, getStatusText, and setDone

        assertDoesNotThrow(() -> {
            EventTask event = new EventTask(TaskTestConstants.VALID_DESCRIPTION_1,
                    TaskTestConstants.VALID_PERIOD_4);

            String[] splitTimings = TaskTestConstants.VALID_PERIOD_4.split(" to ");
            String startDate = splitTimings[0];
            String endDate = splitTimings[1];

            assertFalse(event.isDone());
            assertEquals("\u2718", event.getStatusIcon());
            assertEquals(
                    String.format("[E][\u2718] %s (at: %s)",
                            TaskTestConstants.VALID_DESCRIPTION_1,
                            TaskUtil.getDisplayTime(TaskUtil.getDateFromString(startDate))
                                    + " to "
                                    + TaskUtil.getDisplayTime(
                                            TaskUtil.getDateFromString(endDate))),
                    event.getStatusText());

            event.setDone(true);

            assertTrue(event.isDone());
            assertEquals("\u2713", event.getStatusIcon());
            assertEquals(
                    String.format("[E][\u2713] %s (at: %s)",
                            TaskTestConstants.VALID_DESCRIPTION_1,
                            TaskUtil.getDisplayTime(TaskUtil.getDateFromString(startDate))
                                    + " to "
                                    + TaskUtil.getDisplayTime(
                                            TaskUtil.getDateFromString(endDate))),
                    event.getStatusText());
        });
    }
}
