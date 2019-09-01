package duke.command.test;

import duke.command.DeadlineCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineCommandTest {
    @Test
    public void constructor_instantiateDeadlineCommand_deadlineCommandObject() {
        DeadlineCommand comm = new DeadlineCommand(false, "finish homework", "02/07/2019");
        assertTrue(comm instanceof DeadlineCommand);
    }

    @Test(expected = DukeException.class)
    public void execute_deadlineCommand_executeCommand() {
        DeadlineCommand comm = new DeadlineCommand(false, "finish homework", "02/07/2019");
        comm.execute(new TaskList(), new Ui(), new Storage("wrong/file/path"));

    }
}
