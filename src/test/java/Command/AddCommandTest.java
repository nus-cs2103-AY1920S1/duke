package duke.test.command;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.TaskList;
import duke.task.Task;
import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AddCommandTest {

    public ByteArrayOutputStream modifiedOut = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public AddCommandTest(){
        System.setOut(new PrintStream(modifiedOut));
    }

    @Test
    public void executeToDo() throws DukeException {
        String printedAssert = "Got it. I've added this task: \n" + "  [T]"
                              + " borrow book\nNow you have 1 tasks in the list.";
        Command a = new AddCommand("todo borrow book");
        TaskList tl = new TaskList(new ArrayList<Task>());
        a.execute(tl ,new Ui() , new Storage());
        assertEquals(printedAssert,modifiedOut.toString().trim());
    }
}
