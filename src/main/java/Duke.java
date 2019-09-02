package seedu.duke;

import seedu.duke.Ui;
import seedu.duke.Storage;
import seedu.duke.Parser;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNoSuchCommandException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * Main class of this project.
 * Personal assistant bot to help you keep track of tasks.
 * To run this program, create the Duke object, and use the .run() method.
 * @author Lim Daekoon
 */
public class Duke extends Application {

    // Duke related variables
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    // GUI related variables
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Creates a new Duke object to run.
     */
    public Duke() {

        ui = new Ui();
        storage = new Storage("save.txt");
        this.taskList = storage.load();

        String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.printBlock(new String[] {"Hello! I'm Duke.", "What can I do for you?"});

        // Initializes the parser so that the loop runs at least once
        try {
            this.parser = new Parser("list");
        } catch (Exception e) {
            Ui.printBlock("Unexpected Error");
        }

    }

    /**
     * Runs the Duke program.
     */
    public void run() {

        while (!parser.getType().equals("bye")) {
            try {
                parser = ui.readCommand();

                switch (parser.getType()) {
                case "bye":
                    return;
                case "list":
                    taskList.list();
                    break;
                case "done":
                    taskList.markAsDone(parser);
                    break;
                case "todo":
                    taskList.addTodo(parser);
                    break;
                case "deadline":
                    taskList.addDeadline(parser);
                    break;
                case "event":
                    taskList.addEvent(parser);
                    break;
                case "delete":
                    taskList.deleteTask(parser);
                    break;
                case "find":
                    taskList.find(parser);
                    break;
                default:
                    throw new DukeNoSuchCommandException();
                }

                storage.save(taskList);

            } catch (DukeException e) {
                e.printMessage();
            }
        }
        Ui.printBlock("Bye. Hope to see you again soon!");
    }

    /**
     * Default static main method of this project.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    @Override
    public void start(Stage stage) {
        // Step 1: Adding necessary components to the window
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // Step 2: Formatting the window to look as expected
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
    }
}
