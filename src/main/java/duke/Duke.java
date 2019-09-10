package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.taskList.TaskList;
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
     * constructor to create a Duke Chatbot
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
    public String getDataFromStorage() {
        try {
            return storage.fileContents();
        } catch (FileNotFoundException ex) {
            return UiText.loadingError();
        }
    }

    private void run() {
        try {
            ui.printlnMsg(storage.fileContents());
            ui.printlnMsg(UiText.greeting());
            ui.printlnMsg(UiText.showLine());
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    ui.printlnMsg(UiText.showLine());
                    Command c = Parser.parse(fullCommand);
                    String commandResult = c.execute(tasks, ui, storage);
                    ui.printlnMsg(commandResult);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.printlnMsg(e.getMessage());
                } finally {
                    ui.printlnMsg(UiText.showLine());
                }
            }
        } catch (FileNotFoundException e) {
            ui.printlnMsg(UiText.loadingError());
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public static void main(String[] args) {
        Duke duke = new Duke("src/main/data/duke.txt");
        duke.run();

    }




}
