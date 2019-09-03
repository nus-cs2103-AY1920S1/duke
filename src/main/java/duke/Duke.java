package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Main class for Duke application.
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Duke object with disk storage located at "data/tasks.txt".
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.ui = new Ui();
        this.taskList = new TaskList(storage, ui);
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
