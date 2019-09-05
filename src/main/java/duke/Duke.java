package duke;

import duke.lib.TaskList;
import duke.lib.common.DukeException;
import duke.lib.datahandling.DataStorage;
import duke.lib.datahandling.Parser;
import duke.lib.ui.MainWindow;

import duke.lib.ui.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class of duke, which manages the running of the program.
 */
public class Duke {
    private Parser parser;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Constructor which instantiates a ui, data storage, and attempts loading save dat from the filepath given.
     *
     * @param filePath the location of the save file for duke
     */
    public Duke(String filePath) throws DukeException {
        DataStorage storage = new DataStorage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList();
            tasks.store(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw e;
        }
        parser = new Parser(tasks, storage);
    }

    public boolean isExit() {
        return parser.isExit();
    }
}
