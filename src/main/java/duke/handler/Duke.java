package duke.handler;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalDescriptionException;
import duke.exception.IllegalIndexOfTaskException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class manages duke operation.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;


    /**
     * Class constructor specifying the file path for storage.
     * @param filePath the file path for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Loads tasks from the disk.
     * @throws FileNotFoundException If the file from which to load task is not found.
     */
    public void loadTasks() throws FileNotFoundException {
        tasks = new TaskList(storage.load());
    }

    /**
     * Stores all tasks in the task list to disk.
     * @throws IOException If an input or output exception occurred.
     */
    public void storeTasks() throws IOException {
        storage.store(tasks);
    }

    /**
     * Returns the result of executing the command.
     * @param commandString command to be parsed and executed.
     * @return the result of executing the command.
     * @throws IllegalDescriptionException If the task description is illegal.
     * @throws IllegalIndexOfTaskException If the indices of task is illegal.
     * @throws IllegalCommandException If the command is not recognized.
     */
    public CommandResult execute(String commandString) throws
            IllegalDescriptionException, IllegalIndexOfTaskException, IllegalCommandException {
        Command command = parser.parseCommand(commandString);
        CommandResult result = command.execute(tasks);
        return result;
    }

    /**
     * Returns a string representation of the command result.
     * @param commandResult the result to be shown.
     * @return a string representation of the command result.
     */
    public String getResultUi(CommandResult commandResult) {
        return ui.composeResult(commandResult);
    }

    /**
     * Returns a string representation of an exception occurring during parsing.
     * @param e the exception to be shown.
     * @return a string representation of an exception occurring during parsing.
     */
    public String getParsingErrorUi(Exception e) {
        return ui.showParsingError(e);
    }

    /**
     * Returns a string representation of an exception occurring during storing.
     * @param e the exception to be shown.
     * @return a string representation of an exception occurring during storing.
     */
    public String getStoringErrorUi(IOException e) {
        return ui.showStoringError(e);
    }
}