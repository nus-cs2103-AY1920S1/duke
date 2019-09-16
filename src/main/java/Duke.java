import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver class which contains the main method of the program. Also encapsulates Duke,
 * a to-do list. Each Duke object has a storage file, a list of tasks, and a user
 * interface.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = System.getProperty("user.dir")+"/data/tasks.txt";

    /**
     * Constructor.
     */
    public Duke() {
        try {
            new File("data").mkdir();   // Create storage directory named "data"
            new File("data/tasks.txt").createNewFile(); // Create storage text file named "tasks.txt"
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui = new Ui();
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
        new Duke().run();
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

     public String getResponse(String input) {
         Command c = Parser.parse(input);
         try {
             String response = c.executeForGui(tasks, ui, storage);
             return response;
         } catch (DukeException e) {
             return e.getMessage();
         }
    }

    public String welcomeMessage() {
         return "This is Donna from Suits.\nWhat can I do for you?";
    }
}