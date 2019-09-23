package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageHandler;
import duke.utilities.Parser;
import duke.utilities.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Contains methods relevant to the start up and shut down of the Duke application.
 *
 * @author JXKENG
 */
public class Duke {
    //Solution below adapted from
    //https://stackoverflow.com/a/30121011/
    //FILE_PATH variable is platform independent
    public static final String FILE_PATH = System.getProperty("user.dir")
            + File.separator + "data" + File.separator + "duke.txt";
    private Storage storage = new Storage(FILE_PATH);
    private TaskList tasks = new TaskList();
    private MessageHandler messageHandler = new MessageHandler(tasks, storage);
    private boolean isExit = false;

    /**
     * Get Duke's response given a full command.
     *
     * @param fullCommand A <code>String</code> command
     * @return Duke's response in <code>String</code> format
     */
    public String getResponse(String fullCommand) {
        String response;
        try {
            Command c = Parser.parse(fullCommand);
            response = c.execute(tasks, messageHandler, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = "): OOPS!!! " + e.getMessage();
        }
        return response;
    }

    /**
     * Checks if application should exit.
     *
     * @return a <code>boolean</code> representing if the application should exit
     */
    public boolean applicationShouldExit() {
        return isExit;
    }

    /**
     * Says hi and loads previous state if possible.
     * If not, sets up task file in data folder in current directory <code>./data</code>
     *
     * @return Duke's hi message with a list of where you left off previously
     */
    public String initialize() {
        String message = messageHandler.hiMessage();
        try {
            storage.readFromTasksFileToList(tasks);
            message += "This is where you left off previously:\n";
        } catch (FileNotFoundException e) {
            message += "Fetching failed. " + e.getMessage() + "\n";
            message += "Creating file now...\n";
            File dukeTxt = new File(Duke.FILE_PATH);
            try {
                dukeTxt.createNewFile();
                message += "File created! " + dukeTxt.getCanonicalPath() + "\n";
                message += "Reading file...\n";
            } catch (IOException ioe) {
                message += "\t File creation was not successful. " +
                        "Ensure you have an empty folder named data in your current directory.\n";
                message += "\t Exiting system.";
                return message;
            }

        }

        message += messageHandler.getAllTasksAsString();

        assert !message.isEmpty();

        return message;
    }
}

