package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.ui.MessageHandler;
import duke.utilities.Parser;
import duke.utilities.Storage;

import java.util.logging.Logger;

/**
 * Driver class for Duke operations.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        String response;
        try {
            Command c = Parser.parse(fullCommand);
            response = c.execute(tasks, messageHandler, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = "\u2639 OOPS!!! " + e.getMessage();
        }
        return response;
    }

    public boolean applicationShouldExit() {
        return isExit;
    }

    public String hiMessage() {
        return messageHandler.hiMessage();
    }
}

