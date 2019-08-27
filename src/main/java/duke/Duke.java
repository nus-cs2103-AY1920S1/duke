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

        Label message;
        try {
            taskList = storage.loadData();
            ui.showGreeting();
        } catch (DukeException e) {
            ui.showException(e);
            taskList = new TaskList();
        } finally {
            message = new Label(ui.toString());
            mainWindow.displayMessage(message);
        }
    }

    /**
     * Start executing the duke project.
     */
    public String process(String input) {
        try {
            Command command = parser.parse(input);
            command.execute(taskList, ui, storage);
            if (command.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            ui.showException(e);
        } finally {
            return ui.toString();
        }
    }
}

