package seedu.duke.model;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.Command;
import seedu.duke.logic.command.DeleteCommand;
import seedu.duke.logic.command.DoneCommand;
import seedu.duke.logic.command.RandomCommand;
import seedu.duke.logic.command.SortCommand;
import seedu.duke.logic.command.UndoneCommand;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.task.TaskList;
import seedu.duke.model.task.TaskListHistory;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines the Duke class
 * Contains the main method that is called to run the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TaskListHistory taskListHistory;
    private Ui ui;
    private boolean isExit;

    /**
     * Creates an instance of Duke with a default location of storage files.
     * Files are stored in a text file, inside the current folder where Duke is currently being run.
     */
    public Duke() {
        this(System.getProperty("user.dir")
                + (System.getProperty("os.name").startsWith("Windows") ? "\\data\\duke.txt"
                : "/data/duke.txt"));
    }

    /**
     * Creates an instance of Duke with it's Storage for loading and saving data.
     * @param filePath Directory where the data file is located or is to be located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskListHistory = new TaskListHistory();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            assert tasks != null : "Duke TaskList is not initialised.";
            taskListHistory.add(tasks);
        }
    }

    /**
     * Runs an iteration of the Duke program.
     * Performs the necessary input command given an input.
     * Stores modified Tasks to a history of TaskLists for undo and redo functions.
     * @param input String representation of input from the user.
     */
    private void run(String input) {
        try {
            String fullCommand = input;
            Command c;
            c = Parser.parse(fullCommand, this);
            assert c != null : "Command object to be executed is null.";
            assert tasks != null : "TaskList of this Duke instance is null.";
            assert ui != null : "Ui of this Duke instance is null.";
            assert storage != null : "Storage of this Duke instance is null.";
            c.execute(tasks, ui, storage);
            if (c instanceof AddCommand
                    || c instanceof DoneCommand
                    || c instanceof UndoneCommand
                    || c instanceof DeleteCommand
                    || c instanceof SortCommand
                    || c instanceof RandomCommand) {
                taskListHistory.add(tasks);
            }
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the response of Duke after calling it's run method.
     * @param input String representation of input from the user.
     * @return The response of Duke obtained from it's Ui after calling the run method of Duke.
     */
    public String getResponse(String input) {
        assert input != null : "User input is null.";
        assert input instanceof String : "User input is not a String.";
        run(input);
        return ui.getOutput();

    }

    /**
     * Returns the Ui attribute of this instance of Duke.
     * @return Ui instance employed by this Duke object.
     */
    public Ui getUi() {
        assert ui != null : "Ui of this Duke instance is null.";
        return ui;
    }

    /**
     * Returns a TaskListHistory object stored in this instance of Duke.
     * @return A TaskListHistory object stored in this instance of Duke that contains the timeline of all captured
     *         TaskLists through different operations.
     */
    public TaskListHistory getTaskListHistory() {
        return taskListHistory;
    }

    /**
     * Returns whether Duke has been called to exit after the most recent command.
     * @return A boolean tha denotes whether or not Duke has been called to terminate by user command.
     */
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Sets the TaskList stored in this instance of Duke to be the given one in the TaskList parameter.
     * @param tasks TaskList to replace the current TaskList stored in this instance of Duke.
     */
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Resets TaskListHistory to contain only the current TaskList.
     * WARNING: This action CANNOT be undone.
     */
    public void resetTaskListHistory() {
        this.taskListHistory = new TaskListHistory();
        taskListHistory.add(tasks);
    }
}
