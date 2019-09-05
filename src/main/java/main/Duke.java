package main;

import command.*;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main driver of the program.
 */
public class Duke extends Application {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.printMessage("Hello! I'm Duke\n     What can i do for you?");

        try {
            run();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }

    /**
     * Starts the program sequence.
     */
    public static void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./todoList.txt");
        TaskList task = new TaskList(storage.fileInitialization());

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] temp = input.split(" ");
            while (temp.length == 0) {
                ui.printMessage("Please input something :(");
                input = sc.nextLine();
                temp = input.split(" ");
            }
            Command c = Parser.parse(input);
            c.execute(task, ui, storage);
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            new ExitCommand().execute(task, ui, storage);
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
