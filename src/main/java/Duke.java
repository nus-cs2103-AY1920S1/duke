import duke.command.Command;
import duke.util.*;

import java.io.FileNotFoundException;

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
        setUp();
    }

    /**
     * Starts the program by initialising and run the <code>Duck</code> object. The file path is
     * "/Users/xiaoyu/duke/data/duke.txt" by default.
     *
     * @param args an array of <code>String</code> read from console
     */
    public static void main(String[] args) {
        new Duke("/Users/xiaoyu/duke/data/duke.txt").run();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String setUp() {

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        String listInfo;
        assert taskList != null;
        if (taskList.getTotalTask() > 0) {
            listInfo = ui.showFullList(taskList);
        } else {
            listInfo = ui.showNoTask();
        }
        return ui.showWelcome() + listInfo;
    }

    /**
     * Starts the whole interaction process with the user. At first, it loads and shows the task list from previous
     * storage. Util the user enters "bye" commands, it executes the user's every commands. It also informs user about
     * invalid commands from input during the interaction.
     */
    public void run() {

        setUp();

        boolean isExist = false;
        while (!isExist) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExist = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
