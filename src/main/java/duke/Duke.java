package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private static TaskList tasks = new TaskList();
    private Storage storage;

    /**
     * Entry class to program.
     */
    public Duke() {
        String filePath = "./data/duke.txt";
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException ignore) {
            // Make use of default created empty TaskList if no save file is found
            ui.showSaveFileNotFoundError();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialize and run program.
     */
    public void run() {
        Ui ui = new Ui();
        ui.showWelcome();

        String input;
        while (!(input = ui.readCommand()).equals("bye")) {
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            try {
                storage.save(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
