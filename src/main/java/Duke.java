import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import command.ByeCommand;
import command.Command;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke runs as the main and this is where all the work is initialised at.
 */
public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     *
     * Also instantiates an instance of Storage and TaskList for this instance of Duke.
     */
    public Duke(){
        storage = new Storage();
        ArrayList<Task> list = storage.readFromFile();
        this.taskList = new TaskList(list);
    }

    /**
     * Handles the parsing of inputs and execution of parsed commands.
     *
     * As long as a ByeCommand is not parsed, Duke will continue parsing for more commands. If a ByeCommand is parsed,
     * the while loop is terminated and Duke terminates.
     *
     * @throws IOException When the Parser f
     */
    private String run(String command) throws IOException {
        Parser parser = new Parser();
        //Scanner sc = new Scanner(System.in);

        Command c = parser.parse(command);
        return c.execute(taskList, storage);


        /*while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();

            Command c = parser.parse(nextLine);
            c.execute(taskList, storage);

            if (c instanceof ByeCommand) {
                break;
            }
        }*/
    }


    /**
     * Runs the main method.
     *
     * There are three main steps that occur in the main method.
     * Firstly, an instance of Duke is created.
     * Secondly, the UI prints the start message to the console.
     * Next, the duke.run() method is initiated which will handle the parsing and subsequent processes.
     *
     * @param args Input from the User.
     */
    /*public static void main(String[] args) {
        Duke duke = new Duke();
        UI.start();

        try {
            duke.run();
        } catch (IOException ioE) {
            System.err.println(ioE);
        }
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input, Duke duke) {
        String toReturn = "";
        try{
            toReturn = duke.run(input);
        } catch (IOException io) {
            System.err.println(io);
        }

        return "Duke heard: " + toReturn;
    }
}
