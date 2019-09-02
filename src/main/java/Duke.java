import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;


/**
 * Driver class which contains the main method of the program. Also encapsulates Duke,
 * a to-do list. Each Duke object has a storage file, a list of tasks, and a user
 * interface.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param filePath file path of text file used to load and store tasks.
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

    /**
     * Runs the application. A scanner is instantiated to read standard input.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method, the program's entry point. A Duke object is instantiated to call run(), an instance method.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        File file = new File("data");
        file.mkdir();
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

     public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}