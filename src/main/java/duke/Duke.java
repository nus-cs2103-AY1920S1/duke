package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import duke.command.CommandNotFoundException;
import duke.parser.IncorrectFileFormatException;
import duke.parser.IncorrectNumberOfArgumentsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.command.Command;

/**
 * Represents a Duke - interactive bot.
 * Contains functions to operate the bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        String filepath = "C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt";
        initializeDuke(filepath);
    }

    /**
     * Constructor for class Duke.
     *
     * @param filePath String of file path to read.
     */
    public void initializeDuke(String filePath) {
        Parser.initialize();
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidPathException i) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncorrectFileFormatException | FileNotFoundException f) {
            ui.showLoadingError();
        } catch (NullPointerException n) {
            ui.showIndexError();
        }
    }

    // Stub to reply to GUI
    String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException | CommandNotFoundException | NullPointerException | IOException | IncorrectNumberOfArgumentsException o) {
            //ui.showIndexError();
            output = "error";
        } //ui.showInputError();
        // ui.showCommandNotFoundError();
        // e.printStackTrace();
        //  ui.showIncorrectNumberOfArgument();

        return output;
    }
}
