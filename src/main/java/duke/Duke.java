package duke;

import duke.tasks.TaskList;
import duke.commands.Command;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private MainWindow mainWindow;
    private boolean isExit = false;

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
            taskList = storage.loadData();
            ui.showGreeting();
        } catch (DukeException e) {
            ui.showException(e);
            taskList = new TaskList();
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
            Command command = parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.showException(e);
        } finally {
            return ui.toString();
        }
    }
}

