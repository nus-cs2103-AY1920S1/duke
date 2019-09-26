import duke.command.AddCommand;
import org.junit.jupiter.api.Test;
import duke.util.DukeException;
import duke.util.TaskList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addTodoTest() {
        TaskList testList = new TaskList();
        AddCommand testCommand = new AddCommand();
        testCommand.execute(testList, null, "todo oop");

        assertEquals("[T][✘] oop", testList.getList().get(0).toString());
    }

    @Test
    public void addDeadlineTest() {
        TaskList testList = new TaskList();
        AddCommand testCommand = new AddCommand();
        testCommand.execute(testList, null, "deadline return book /by 2/12/2019 1800");

        assertEquals("[D][✘] return book (by: 2/12/2019 1800)", testList.getList().get(0).toString());
    }
}
