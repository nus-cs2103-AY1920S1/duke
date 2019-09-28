import duke.command.Command;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Parser;


import java.io.IOException;

/**
 * This is the driver class that utilises existing classes to receive and respond to the user's commands. It loads and
 * shows the task list from previous storage at the beginning. Util the user enters "bye", it responds to users' input
 * and modifies the current task list. It also informs user about invalid commands from input during the interaction.
 * When the user enters "bye" command, it stores the current task list and terminates the process.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * This is a sole constructor specifying the file path to load data from and write data to. It initialises a
     * <code>Ui</code>  to help with input and output, and a <code>Storage</code> object to loading and storing data.
     *
     * @param filePath a string specifying the path of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Loads data to take list and shows welcome message to the user.
     *
     * @return  a string welcoming the user
     */
    public String setUp() {
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException | DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        return ui.showWelcome();
    }

    /**
     * Executes the command and shows corresponding reply.
     *
     * @param input  a string representing the user's command
     * @return       a string showing the executing result
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
