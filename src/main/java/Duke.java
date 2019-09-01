import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Driver class which contains the main method of the program. Also encapsulates Duke,
 * a to-do list. Each Duke object has a storage file, a list of tasks, and a user
 * interface.
 */
public class Duke extends Application {

//    private Storage storage;
//    private TaskList tasks;
//    private Ui ui;
//
//    /**
//     * Constructor.
//     *
//     * @param filePath file path of text file used to load and store tasks.
//     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }
//
//    /**
//     * Runs the application. A scanner is instantiated to read standard input.
//     */
//    public void run() {
//        ui.showWelcome();
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            try {
//                String input = sc.nextLine();
//                Command c = Parser.parse(input);
//                c.execute(tasks, ui, storage);
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Main method, the program's entry point. A Duke object is instantiated to call run(), an instance method.
//     *
//     * @param args an array of command-line arguments for the application
//     */
//    public static void main(String[] args) {
//        File file = new File("data");
//        file.mkdir();
//        new Duke("data/tasks.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}