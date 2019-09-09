package duke.handler;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.CommandType;
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
     * Parses the command.
     * @param commandString command to be parsed.
     * @return a string representation of the response to the command.
     */
    public String parse(String commandString) {
        try {
            Command command = parser.parseCommand(commandString);
            CommandResult result = command.execute(tasks);
            if (result.getCommandType() == CommandType.Exit) {
                try {
                    storeTasks();
                } catch (IOException e) {
                    return ui.showStoringError(e);
                }
            }
            return ui.composeResult(result);
        } catch (Exception e) {
            return ui.showParsingError(e);
        }
    }
}