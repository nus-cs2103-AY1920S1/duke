package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageHandler;
import duke.utilities.Parser;
import duke.utilities.Storage;


/**
 * Contains methods relevant to the start up and shut down of the Duke application.
 *
 * @author JXKENG
 */
public class Duke {
    public static final String filePath = "C:/Users/jxken/Desktop/Github/duke/data/duke.txt";
    private Storage storage = new Storage(filePath);
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
     * Get Duke's hi message when initializing Duke.
     *
     * @return Duke's hi message with a list of where you left off previously
     */
    public String hiMessage() {
        return messageHandler.hiMessage();
    }
}

