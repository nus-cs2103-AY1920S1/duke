package seedu.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke() {}

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String command = ui.readCommand();
        new Parser().parse(command, ui, tasks, storage.path);
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        new Duke("C:\\duke\\src\\data\\tasklist.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        helloWorld.setFont(Font.font("Times New Roman",80));
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();

    }
}