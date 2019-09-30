package duke;

import static duke.ui.UiText.GREETING;
import static duke.ui.UiText.LOADING_ERROR;
import static duke.ui.UiText.SHOW_LINE;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UiText;

import java.io.FileNotFoundException;

/**
 * Duke is  a Personal Assistant Chatbot that helps a person to keep track of various things.
 * @author Yang Shuting
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private  UiText ui;
    private boolean isExit;

    /**
     * constructor to create a Duke Chatbot.
     *
     * @param filePath The path for the text file that storages
     *                 the data
     */

    public Duke(String filePath) {
        ui = new UiText();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {
        this(Storage.DEFAULT_PATH);
    }

    /**
     * returns a string of duke's respond base on the user input command.
     * @param text user input text.
     * @return duke's response
     */
    public String getResponse(String text) {
        try {
            Command c = Parser.parse(text);
            isExit = c.isExit();
            String commandResult = c.execute(tasks, ui, storage);
            return commandResult;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * read the information from the storage and display to the user.
     * @return content from the storage.
     */
    public String getDataFromStorage() {
        try {
            return storage.fileContents();
        } catch (FileNotFoundException ex) {
            return LOADING_ERROR;
        }
    }

    private void run() {
        ui.printlnMsg(getDataFromStorage());
        ui.printlnMsg(GREETING);
        ui.printlnMsg(SHOW_LINE);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printlnMsg(SHOW_LINE);
                Command c = Parser.parse(fullCommand);
                String commandResult = c.execute(tasks, ui, storage);
                ui.printlnMsg(commandResult);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printlnMsg(e.getMessage());
            } finally {
                ui.printlnMsg(SHOW_LINE);
            }
        }

    }

    /**
     * check if the user has key in exit command.
     * @return true if user has enter the exit command, or else otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * main method in the Duke.
     * @param args system input
     */
    public static void main(String[] args) {
        Duke duke = new Duke("src/main/data/duke.txt");
        duke.run();

    }




}
