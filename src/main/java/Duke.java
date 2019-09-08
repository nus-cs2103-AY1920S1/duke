import duke.command.Command;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke {
    /** User Interface of Duke that handles input and output to and from the command line. */
    private Ui ui;
    /** Storage where the Tasks are retrieved from and stored to. */
    private Storage storage;
    /** List of Tasks Duke is currently tracking. */
    private TaskList tasks;

    /**
     * Constructor for Duke that instantiates the Ui and Storage classes.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        loadTasksFromStorage();
    }

    public static void main(String[] args) {
        new Duke().runCli();
    }

    /**
     * Returns the response from Duke based on user's input from GUI.
     *
     * @param input Input from user.
     * @return Output string from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeGui(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMsg(e);
        }
    }

    /**
     * Runs and starts the Duke chatbot program.
     */
    public void runCli() {
        ui.printGreetingMsg();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                isExit = c.isExit();
                c.executeCli(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printErrorMsg(e);
            }
        }

        ui.printExitMsg();
    }

    /**
     * Loads tasks from storage and assign it to TaskList.
     * If the file is not found or the file cannot be read correctly,
     * a empty TaskList is assigned to task.
     */
    private void loadTasksFromStorage() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printErrorMsg(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the Ui instance associated with Duke.
     *
     * @return Ui instance associated with Duke.
     */
    public Ui getUi() {
        return ui;
    }
}
