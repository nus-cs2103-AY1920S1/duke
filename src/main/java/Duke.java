import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for Duke.
 */

public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    static Ui ui = new Ui();
    static ArrayList<Task> taskList = new ArrayList<>(100);
    static InputParser inputParser = new InputParser(taskList);
    static Scanner scan = new Scanner(System.in);
    static String input = "";

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public String getResponse(String input) throws IOException {
        while (!input.equals("bye")) {
            input = scan.nextLine();
            inputParser.determineAction(input);
        }
        return null;
    }

    //public String getResponse(String input) {
    //}

    public enum Action {
        ADD,
        REMOVE,
        DONE,
        FIND
    }

    public static void main(String[] args) throws IOException {

        try {
            FileReading.checkFileExists(taskList);
        }  catch (IOException e) {
            System.out.println("Oops. something went wrong with the duke.txt file");
        }

        ui.sayGreeting();

        while (!input.equals("bye")) {
            input = scan.nextLine();
            inputParser.determineAction(input);
        }

        ui.sayGoodbye();
    }
}
