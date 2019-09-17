package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.exception.DukeIoException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Loads duke with task data.
     *
     * @param filePath the file path of the data file from project root.
     */
    public Duke(String filePath) {
        initLogger();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            System.err.println(ui.showError(e));
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    /**
     * Default constructor. JavaFX GUI can't run without declaring this.
     */
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Executes a command.
     *
     * @param fullCommand the command with parameters.
     * @return the response of the command.
     */
    public CommandResponse getResponse(String fullCommand) {
        Command c = Parser.parse(fullCommand);
        return c.execute(taskList, ui, storage);
    }

    private void initLogger() {
        LogManager.getLogManager().reset(); // remove all handlers for ALL loggers
        logger.setLevel(Level.ALL); // global switch for all handlers

        // Log only critical errors for users
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        try {
            // Log everything else for developer
            FileHandler fh = new FileHandler("myLogger.log");
            fh.setLevel(Level.FINE);
            fh.setFormatter(new SimpleFormatter()); // MM DD, YYYY HH:MM:SS O <name> <msg>
            logger.addHandler(fh);
        } catch (IOException e) {
            // ignore
            logger.log(Level.SEVERE, "File logger not working.");
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
