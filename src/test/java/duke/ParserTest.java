package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static duke.task.TaskType.DEADLINE;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        String[] args = {"live", "/by", "2/12/2019", "1800"};
        AddCommand command = new AddCommand(DEADLINE, args, false);
        assertArrayEquals(
                command.getArgs(),
                ((AddCommand) Parser.parse("deadline live /by 2/12/2019 1800")).getArgs());
    }
}