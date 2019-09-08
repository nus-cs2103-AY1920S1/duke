import command.AddCommand;
import command.DoneCommand;
import org.junit.jupiter.api.Test;
import util.DukeException;
import util.TaskList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void doneTest() throws IOException, DukeException {
        TaskList testList = new TaskList();
        AddCommand testCommand = new AddCommand();
        testCommand.execute(testList, null, null, "todo oop");
        DoneCommand doneCommand = new DoneCommand();
        doneCommand.execute(testList, null, null, "done 1");

        assertEquals("[T][✓] oop", testList.getList().get(0).toString());
    }
}
