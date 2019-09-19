package duke.command;

import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;
import duke.ui.UserInterfaceStub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    @Test
    public void execute_invalidCommand_WrongDateFormatException() {
        try {
            new AddCommand("E", "Testing", "Sunday").execute(new TaskListStub(),
                    new UserInterfaceStub(), new StorageStub());
            fail();
        } catch (Exception e) {
            assertEquals(" \u2639 OOPS!!!  Please follow the correct datetime format(dd/mm/yyyy HHMM)",
                    e.toString());
        }
    }

}
