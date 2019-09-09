package duke.command;

import duke.task.TaskTestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventCommandTest {
    @Test
    void validate_missingDelimeter_throwsDukeInvalidArgumentException() {
        String[] commandArgs = new String[] { "desc", "invalid delimiter", "timing" };
        EventCommand eventCommand = new EventCommand(commandArgs);

        assertThrows(DukeInvalidArgumentException.class,
                () -> eventCommand.validate(null, null, null));
    }

    @Test
    void validate_validArguments_setsAddTask() {
        String[] commandArgs = new String[] { "desc", "/at", TaskTestConstants.VALID_PERIOD_1 };
        EventCommand eventCommand = new EventCommand(commandArgs);
        assertDoesNotThrow(() -> eventCommand.validate(null, null, null));

        assertNotNull(eventCommand.taskToAdd);
    }
}
