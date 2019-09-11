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
    private String filepath;

    Duke() {
        filepath = "C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt";
        initializeDuke(filepath);
    }

    /**
     * Constructor for class Duke.
     *
     * @param filePath String of file path to read.
     */
    private void initializeDuke(String filePath) {
        Parser.initialize();
        storage = new Storage(filePath);
        ui = new Ui();
    }

    void performDukeStartup() throws IncorrectFileFormatException, FileNotFoundException {
        try {
            tasks = new TaskList(storage.load(ui));
        } catch (InvalidPathException i) {
            tasks = new TaskList();
            throw new InvalidPathException(filepath, ui.getLoadingError());
        } catch (IncorrectFileFormatException j) {
            throw new IncorrectFileFormatException(ui.getLoadingError());
        } catch (NullPointerException n) {
            throw new NullPointerException(ui.getIndexError());
        } catch (FileNotFoundException f1){
            throw new FileNotFoundException(ui.getLoadingError());
        }
    }

    // Stub to reply to GUI
    String getDukeResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException e){
            output = "Incorrect input value.\nPlease check again.";
        } catch (CommandNotFoundException c) {
            output = "Command not found.\nPlease check again.";
        } catch (NullPointerException n){
            output = "Internal error encountered." + " (Null Ptr)";
        } catch (IOException i){
            output = "Internal error encountered." + " (IO exception)";
        } catch(IncorrectNumberOfArgumentsException a){
            output = "Incorrect command format.\nPlease check again.";
        }
        return output;
    }

    String getDukeWelcome(){
        return ui.getWelcome();
    }
}
