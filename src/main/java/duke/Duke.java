package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * constructor for Duke.
     *
     * @param filePath which specifies the path for the .txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("./data/tasks.txt");
    }

    /**
     * Instantiate a Duke object and run it.
     *
     * @param args standard main args
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    /**
     * Runs process of Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "duke.Duke heard: " + input;
    }
}
