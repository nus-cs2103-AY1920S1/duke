package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.gui.DialogBox;
import duke.storage.Storage;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke extends Application {
    private final Image IMAGE_USER = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image IMAGE_DUKE = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    // JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            ui.showError(e);
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    // JavaFX GUI won't run without this.
    public Duke() { }

    /**
     * The main loop for Duke.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * The entry point for the JavaFX application.
     *
     * @param primaryStage the stage to render the JavaFX nodes.
     */
    @Override
    public void start(Stage primaryStage) {
        // Step 1: Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // 2. Format the window to look as expected
        // stage configuration
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        // Anchor pane configuration
        mainLayout.setPrefSize(400.0, 600.0);

        // Scroll pane configuration
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0); // set scrolled all the way down
        scrollPane.setFitToWidth(true);

        // Vertical box configuration
        // scroll down to the end when VBox's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Text field configuration
        userInput.setPrefWidth(325.0);

        // Button configuration
        sendButton.setPrefWidth(55.0);

        // Anchor nodes in specified positions
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // 3. Add functionality to handle user input.
        // Allow entering input by clicking the "SEND" button
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // Allow entering input by pressing "ENTER"
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and appending it to
     * the dialog container. Clears user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(IMAGE_USER)),
                DialogBox.getDukeDialog(dukeText, new ImageView(IMAGE_DUKE))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
