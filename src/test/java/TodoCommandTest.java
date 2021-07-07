import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.TodoCommand;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {

    @Test
    // Since this method returns void, testing it using its side effects
    public void executingCommand_todoCommand_success() throws DukeException {
        // Setting up actual TaskList
        TaskList t = new TaskList();
        Ui ui = new Ui();
        Storage s = new Storage("data/tasksTest.txt");
        new TodoCommand("buy groceries").execute(t, ui, s);

        // Setting up expected list of tasks
        LinkedList<Task> tExpected = new LinkedList<>();
        tExpected.add(new Todo("buy groceries"));

        // Comparing the list of tasks
        assertEquals(tExpected.get(0).toString(), t.getTasks().get(0).toString());
    }

}
