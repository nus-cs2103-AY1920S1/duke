package seedu.duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class of Duke application.
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Scanner sc;

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        sc = new Scanner(System.in);
        parser = new Parser(sc, storage);
    }

    /**
     * Runs the app. Method keeps running as long as parser does not return an exit response.
     */
    void run() {
        ui.greeting();
        int response;
        do {
            ui.printLine();
            String command = sc.next();
            ui.printBreakLine();
            response = parser.process(command);
            if (response == -1) {
                ui.goodbye();
            }
            ui.printBreakLine();
        } while (response != -1);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
