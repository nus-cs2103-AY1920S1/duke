package duke;

import duke.exception.DukeException;
import duke.model.Model;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.commands.Command;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Model model;
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private MainWindow mainWindow;

    /**
     * Start the gui application.
     * @param stage stage object of the application
     */
    @Override
    public void start(Stage stage) {
        parser = new Parser();
        storage = new Storage();
        ui = new Ui();
        mainWindow = new MainWindow(this);
        mainWindow.setGui(stage);

        Label initialMessageLabel;
        try {
            model = storage.loadData();
            ui.showGreeting();
        } catch (DukeException e) {
            ui.showException(e);
            model = new Model();
        } finally {
            String initialMessage = ui.toString();
            assert !initialMessage.isEmpty() : "Empty message at the start!";

            initialMessageLabel = new Label(initialMessage);
            mainWindow.displayMessage(initialMessageLabel);
        }
    }

    /**
     * Start executing the duke project.
     */
    public String process(String input) {
        try {
            Command command = parser.parse(model, input);
            command.execute(model, ui, storage);
        } catch (DukeException e) {
            ui.showException(e);
        } finally {
            return ui.toString();
        }
    }
}

