package duke.test.command;

import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.Command;
import duke.task.TaskList;
import duke.task.Task;
import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DoneCommandTest {

    public ByteArrayOutputStream modifiedOut = new ByteArrayOutputStream();

    public DoneCommandTest(){
        System.setOut(new PrintStream(modifiedOut));
    }

    @Test
    public void doneToDo_exceptionThrown() throws DukeException {
        try {
            Command a = new AddCommand("todo borrow book");
            Command d = new DoneCommand("done");
            Ui u = new Ui();
            Storage s = new Storage();
            TaskList tl = new TaskList(new ArrayList<Task>());
            a.execute(tl, u, s);
            d.execute(tl, u, s);
            fail("Exception was not thrown for Done Command Test");
        } catch (DukeException e) {
            String printedAssert = "\u2639 " +  "OOPS!!! The description of done must have a value.";
            assertEquals(printedAssert, e.getMessage());
        }
    }
}
