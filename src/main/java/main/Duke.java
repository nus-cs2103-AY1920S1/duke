package main;

import command.Command;
import command.ExitCommand;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main driver of the program.
 */
public class Duke{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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

    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

