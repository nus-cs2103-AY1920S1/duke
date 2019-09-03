package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Main class that brings together all the implemented classes to execute the Duke
 * tasking logic.
 */
public class Duke {

    public static String saveFilePath;
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param saveFilePath String representing the path to the file in which Tasks
     *                     will be saved
     */
    public Duke(String saveFilePath) {
        Duke.saveFilePath = saveFilePath;
        this.ui = new Ui();
        this.storage = new Storage(Duke.saveFilePath);
        try {
            this.allTasks = this.storage.load();
        } catch (DukeException e) {
            ui.printErrorMsg(e);
            this.allTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Keeps looping through to check the console for user input. Will terminate
     * when the user inputs "bye" to the console.
     */
    public static void main(String[] args) {
        Duke d = new Duke("data/savedTasks.txt");
        d.run();
    }

    public void run() {
        ui.printWelcomeMsg();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.ui, this.storage, this.allTasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                //This is the highest level at which a DukeException is caught.
                //Most DukeExceptions will be caught and handled at this level.
                ui.printErrorMsg(e);
            } catch (NoSuchElementException e) {
                ui.printSentence("Please enter a command!");
            }
        }

        ui.printExitMsg();
    }
}
