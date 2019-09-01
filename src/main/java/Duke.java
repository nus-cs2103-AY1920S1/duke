import duke.command.Command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.parser.Parser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is the main Duke class
 */

public class Duke extends Application {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        new Duke("C:/Users/Low Cheng Yi/Desktop/CS2103/duke/src/data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public void run() {
        ui.showWelcome();
        ui.list(tasks.getTasks());
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.save();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    public static void main(String[] args) {
        new Duke("C:/Users/Low Cheng Yi/Desktop/CS2103/duke/src/data/tasks.txt").run();
    }

}