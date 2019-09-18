import java.util.ArrayList;
import java.io.File;

import commands.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import tasks.Task;
import tasks.TaskList;

// For JavaFX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

public class Duke extends Application {
    // String Constants used for Duke output
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private int size;

    // JavaFX Constants
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Command c = Parser.parse(input);
            sb.append(c.execute(tasks, ui, storage) + "\n");
        } catch (DukeException err) {
            sb.append(err.getMessage());
        }
        return sb.toString();
    }

    /**
     * Instantiates a new Duke object.
     * Load an existing .txt file for the list of tasks stored.
     *
     * @param filePath The file path of the list .txt file
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile()); // add existing tasks from txt file onto ArrayList
            size = tasks.getSize(); // get existing size of list
            storage.closeFile();
        } catch (DukeException e) {
            ui.loadError();
            ui.createFile();
            ArrayList<Task> list = new ArrayList<>();
            tasks = new TaskList(list);
            File file = new File(filePath); // create new .txt file with provided filepath
            try {
                file.createNewFile();
            } catch (Exception err) {
                System.out.println("Unable to create new file."); // Unlikely
            }
        }
    }

    /**
     * Run the Duke application.
     * Accepted commands: list, done, delete, find
     * Accepted tasks: todo, event, deadline
     */
    public void run() {

        System.out.println(ui.welcomeStatement());
        boolean isExit = false;
        Storage storage = this.storage;
        TaskList tasks = this.tasks;

        // Initial opening introduction and prompt for user input
        System.out.println(ui.openingStatement());
        try {
            Command c = Parser.parse("remind");
            System.out.println(c.execute(tasks, ui, storage));
        }
        catch (DukeException err) {
            System.out.println("No reminders.");
        }
        while (!isExit) {
            try {
                String input = ui.readCommand();

                Command c = Parser.parse(input);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            ui.nextCommand();

            try {
                storage.saveFile(tasks, storage.getFilePath());
            } catch (DukeException e) {
                ui.saveError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("list.txt").run();
    }
}

