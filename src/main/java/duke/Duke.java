package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Duke Program.
 */
public class Duke extends Application {


    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private static final String FILE_PATH = "data/duke.txt";

    @Override
    public void init() throws Exception {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Constructor of Duke object.
//     * @param filePath path to the input text file
     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (IOException | DukeException e) {
//            ui.showError(e.getMessage());
//            tasks = new TaskList();
//        }
//    }

//    public static void main(String[] args) {
////        new Duke("data/duke.txt").run();
//        launch(args);
//    }


    /**
     * Runs the duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "number"));
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.run(primaryStage);
    }
}
