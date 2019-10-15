import duke.commands.Command;
import duke.commands.CommandType;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.TaskList;
import duke.core.Parser;

import duke.errors.DukeException;
import duke.tasks.Task;

import java.io.IOException;


/**
 * The driver class to run user interface of Duke. Duke provides commands to add different tasks,
 * list out tasks, marking tasks as done, deleting tasks and storing the tasks into a file for
 * retrieval after reboot
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;


    /**
     * Initialises a new Duke application.
     * @throws DukeException Thrown when parts of the command cannot be executed.
     */
    Duke() throws DukeException {
        Storage storage = Storage.createStorageIfRequired();
        this.taskList = new TaskList(storage.load(), storage);
        Task.setTaskList(taskList);
        this.ui = new Ui();
    }


    Response getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return new Response(c.execute(taskList, ui),false);
        } catch (IllegalArgumentException | DukeException | IOException error2) {
            return new Response(ui.printErrorMessage(error2),true);
        }
    }


}
   
