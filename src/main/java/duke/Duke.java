package duke;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This program is an interactive task list that takes in several
 * preset commands from the user to create tasks, view the list of
 * tasks and mark each of it as completed. This class contains the
 * main method and is responsible for all input/output and Task creation.
 * @author Gabriel Ong
 *
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a instance of duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("." + File.separator + "data");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs duke to start taking in input from user.
     */
    public void run() {
        ui.greetHello();

        this.tasks = ui.runInputLoop(this.tasks);

        //Save file before exiting
        try {
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            this.ui.showError(e);
        }

        this.ui.greetBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

}
