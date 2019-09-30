package duke.test.command;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
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

public class DeleteCommandTest {

    public ByteArrayOutputStream modifiedOut = new ByteArrayOutputStream();

    public DeleteCommandTest(){
        System.setOut(new PrintStream(modifiedOut));
    }

    @Test
    public void deleteToDo() throws DukeException {
        Command a = new AddCommand("todo borrow book");
        Command d = new DeleteCommand("delete 1");
        Ui u = new Ui();
        Storage s = new Storage();
        TaskList tl = new TaskList(new ArrayList<Task>());
        a.execute(tl, u, s);
        d.execute(tl, u , s);
        assertEquals(0, tl.getSize());
    }

    public void deleteToDo_exceptionThrown() throws DukeException {
        try {
            Command a = new AddCommand("todo borrow book");
            Command d = new DeleteCommand("delete");
            Ui u = new Ui();
            Storage s = new Storage();
            TaskList tl = new TaskList(new ArrayList<Task>());
            a.execute(tl, u, s);
            d.execute(tl, u, s);
            fail("Exception was not thrown for Delete Command Test");
        } catch (DukeException e) {
            String printedAssert = "\u2639 " +  "OOPS!!! The description of delete must have a value.";
            assertEquals(printedAssert, e.getMessage());
        }
    }
}
