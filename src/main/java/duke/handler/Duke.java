package duke.handler;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.CommandType;
import duke.exception.IllegalDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class representing the Duke App.
 */
public class Duke {
    private static final String FILEPATH = "/Users/jiangyuxin/Documents/sem1/cs2103/duke/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;


    /**
     * Class constructor specifying the file path for storage.
     * @param filePath the file path for storage.
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    public void loadTask() throws FileNotFoundException, IllegalDescriptionException {
        tasks = new TaskList(storage.load());
    }

    public String parse(String command) {
        while (true) {
            try {
                Command cmd = parser.parseCommand(command);
                CommandResult result = cmd.execute(tasks);
                if (result.getCommandType() == CommandType.Exit) {
                    try {
                        storage.store(tasks);
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

    /**
     * Main method.
     * @param args Unused.
     */
    public static void main(String[] args) {
    }
}