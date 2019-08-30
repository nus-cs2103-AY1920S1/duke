import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main Class of the Application, which requires the workings of all packages.
 */
public class Duke extends Application {

    /**
     *  Represents the scroll pane for the application.
     */
    private ScrollPane scrollPane;

    /**
     *  Represents the text box for the applications.
     */
    private VBox dialogContainer;

    /**
     * Represents the text field for the application.
     */
    private TextField userInput;

    /**
     * Represents the send button for the application.
     */
    private Button sendButton;

    /**
     * Represents the rendered scene for the application.
     */
    private Scene scene;

    /**
     * Represents the reader and the writer for the output text file.
     */
    private Storage storage;

    /**
     * Represents the list of tasks stored in the application.
     */
    private TaskList taskList;

    /**
     * Represents the interface which handles user input and interactions.
     */
    private Ui ui;

    /**
     * Parses out the user input for ui recognition.
     */
    private DataParser parser;

    /**
     * Parses out the date to a readable format for the ui.
     */
    private DateParser dateHelper;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Creates a new Duke class which contains all packages to process user input and file input.
     * If no file is found, a new task list is created instead.
     * If a file is found, data is retrieved from the file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        parser = new DataParser();
        dateHelper = new DateParser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Main Logic of the Code, which runs based on user input given by the parsers.
     * Continues to create and execute commands till there is no more user input left.
     */
    public void run() {
        ui.sendGreeting();
        boolean hasTerminated = false;

        while (!hasTerminated) {
            if (!parser.hasAnymoreData()) {
                break;
            }

            try {
                parser.readInput();
                Command c = parser.findCommand();
                hasTerminated = c.isExit;
                c.execute(taskList, ui, storage, parser, dateHelper);
            } catch (DukeException error) {
                ui.sendErrorMessage(error);
            }
        }
        ui.sendFarewell();;
    }

    @Override
    public void start(Stage stage) {

        // Set Stage
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // Set Features
        stage.setTitle("Duke: Abstraction");
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
    }

}
