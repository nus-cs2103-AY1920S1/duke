package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TodoCommandTest {
    @Test
    void validate_validArguments_setsAddTask() {
        String[] commandArgs = new String[] { "lorem", "ipsum", "lipsum" };
        TodoCommand todoCommand = new TodoCommand(commandArgs);
        assertDoesNotThrow(() -> todoCommand.validate(null, null, null));

        assertNotNull(todoCommand.taskToAdd);
    }
}
