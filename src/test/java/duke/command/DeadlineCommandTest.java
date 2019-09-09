package duke.command;

import duke.task.TaskTestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineCommandTest {
    @Test
    void validate_missingDelimeter_throwsDukeInvalidArgumentException() {
        String[] commandArgs = new String[] { "desc", "invalid delimiter", "timing" };
        DeadlineCommand deadlineCommand = new DeadlineCommand(commandArgs);

        assertThrows(DukeInvalidArgumentException.class,
                () -> deadlineCommand.validate(null, null, null));
    }

    @Test
    void validate_validArguments_setsAddTask() {
        String[] commandArgs = new String[] { "desc", "/by", TaskTestConstants.VALID_DATE_1 };
        DeadlineCommand deadlineCommand = new DeadlineCommand(commandArgs);
        assertDoesNotThrow(() -> deadlineCommand.validate(null, null, null));

        assertNotNull(deadlineCommand.taskToAdd);
    }
}
