import task.Task;
import tool.Parser;
import tool.Storage;
import tool.TaskList;
import tool.Ui;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Duke extends Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("src/main/java/data/duke.txt");
        Ui ui = new Ui();
        ui.hi();
        TaskList commands = new TaskList(storage.load(new ArrayList<Task>())); //TODO
        Parser p = new Parser(commands, storage, ui);
        while(scanner.hasNextLine()) {
            String x = scanner.nextLine();
            p.parse(x);
            if (x.equals("bye")) {
                scanner.close();
                break;
            }

        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
