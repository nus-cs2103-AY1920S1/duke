import command.Command;
import main.DukeException;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Duke extends Application {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        String filePath = "/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt";
        // Currently hardcoded.
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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

    /*
    public static void main(String[] args) {

        System.out.println("Enter filepath for task list:");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        Duke duke;
        if (filePath.equals("default")) {
            duke = new Duke("/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt");
        } else {
            duke = new Duke(filePath);
        }

        duke.run();
    }
    */


    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}