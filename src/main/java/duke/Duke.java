package duke;

import duke.command.Command;
import duke.task.TaskList;

import javafx.application.Application;
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
import javafx.stage.Stage;

/**
 * Main class for the Duke application.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User1.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Dukey.png"));

    /**
     * Provides a constructor with zero parameters for JavaFX Launcher
     * to use.
     */
    public Duke() {
        // temporary: hardcoded storage file path
        storage = new HardDiskStorage("/data/duke.txt");
        tasks = new TaskList();
        ui = new TextUi(); // temporary
    }

    /**
     * Sets up Duke's user interface, storage, and task list.
     *
     * @param filePath  Path to data file
     */
    public Duke(String filePath) {
        ui = new TextUi();
        storage = new HardDiskStorage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initialises and runs the Duke application.
     *
     * @param args  Standard arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Sets the stage and scene for JavaFX, using the given Stage.
     *
     * @param stage     Stage to be set
     */
    @Override
    public void start(Stage stage) {
        // Step 1: Set up required components

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2: Format window

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));

        // Add welcome message

        Label dukeWelcome = new Label("Welcome to Duke!\nHow may I help you?");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeWelcome, new ImageView(duke))
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's response, then adds them to the dialog container. Clears user
     * input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Executes the user input and returns Duke's response.
     *
     * @param input     String of user-given input
     * @return          String of Duke's response to given input
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String dukeResponse = c.execute(tasks, ui, storage);
            return dukeResponse;
        } catch (DukeException e) {
            return "Sorry, " + e.getMessage();
        }
    }

    /**
     * Runs the main application by interacting with user input.
     *
     * <p>Duke begins by printing a welcome message. Subsequently, it scans
     * for user input, then validates and processes it accordingly. This is
     * repeated until the command to exit ("bye") is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
                // TODO: Add "help" feature: list all supported commands
            } finally {
                ui.showLine();
            }
        }
    }

}
