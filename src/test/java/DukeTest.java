import duke.commands.AddCommand;
import duke.exceptions.DateException;
import duke.exceptions.DukeException;
import duke.managers.DateTime;
import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    DateTime dt = new DateTime();

    /* this is the naming convention for the features
    @Test
    public void featureUnderTest_testScenario_expectedBehaviour() {

    }
    */

    @Test
    public void dateTimeFormatting_dateGiven_dateFormatReturned() throws DateException {
        assertEquals("12th of December 2019, ", dt.getDate("12/12/2019"));
    }

    @Test
    public void addCommand_commandGiven_taskAddedInRequiredFormat() throws IOException, DukeException {
        String addInstruction = "todo return book";
        String[] details = addInstruction.split(" ");
        AddCommand newAdd = new AddCommand(details);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./tasks.txt");
        newAdd.execute(taskList, ui, storage);
        Task addedTask = storage.load().get(0);
        assertEquals("[T][X] return book ", addedTask.toString());
    }
}
