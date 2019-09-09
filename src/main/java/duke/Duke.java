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
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this(Storage.DEDAULT_PATH);
    }

    public String getResponse(String text) {
        boolean isExit;
        try {
            Command c = Parser.parse(text);
            isExit = c.isExit();
            String commandResult = c.execute(tasks, ui, storage);
            return commandResult;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


    private void run() {
        try {
            ui.greeting();
            storage.printFileContents();
            ui.printlnMsg(UiText.showLine());
            boolean isExit = false;
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
            ui.printlnMsg(UiText.LoadingError());
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("src/main/data/duke.txt");
        duke.run();

    }




}
