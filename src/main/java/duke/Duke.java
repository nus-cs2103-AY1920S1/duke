package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A task list that supports several basic features:
 * 1) Addition and deletion of three types of task.
 * 2) Ability to mark tasks as done.
 * 3) Ability to search for expressions in given tasks.
 * 4) Ability to print current list of tasks.
 */
public class Duke {
    /**
     * The TaskList object which abstracts out a list of tasks.
     */
    private TaskList taskList;
    
    /**
     * The Storage object which loads and writes data to the hard drive.
     */
    private Storage storage;
    
    /**
     * The Ui object which scans input and prints feedback to the user.
     */
    private Ui ui;
    
    public static boolean isExitRunLoop;
    
    /**
     * Initializes a Duke object.
     * The Duke constructor has no parameters due to a quirk in javafx.application.Application, which does not work
     * with a constructor with parameters. This was the best workaround I could find after 4 days of trying.
     */
    public Duke() {
        this("CurrentTaskList.txt");
    }
    
    /**
     * Initializes the Ui, Storage, and TaskList objects.
     * This method serves as a proxy for the Duke constructor method, which is left empty due to a quirk in
     * javafx.application.Application, which does not work with a constructor with parameters.
     *
     * @param filePath The file path of the hard drive location to read and write from, as a String.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadSavedList());
        } catch (DukeException | IOException e) {
            ui.print(ui.showError(e));
            taskList = new TaskList();
        }
    }
    
    /**
     * Scans and parses commands given by the user.
     * Modifies the Tasks in the TaskList object based on the commands received by the user.
     */
    private void run() {
        ui.print(ui.showHello());
        while (!Duke.isExitRunLoop) {
            ui.print(getResponse(ui.getNextLine()));
        }
    }
    
    /**
     * Gets a response String from an input String.
     *
     * @param input The input String.
     * @return Returns the response String.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException | IOException e) {
            return ui.showError(e);
        }
    }
    
    public static void main(String[] args) {
        new Duke("CurrentTaskList.txt").run();
    }
    
    
    //Add dummy comment for A-Assertions.
}
