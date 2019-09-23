package duke.program;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TextStorage;
import duke.task.TaskList;
import duke.ui.gui.DialogBox;
import duke.util.Parser;
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

import java.util.List;

public class DukeGui extends Application {
    private Storage storage;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        // Get the storage filepath from the parameters
        List<String> params = getParameters().getRaw();
        assert params.size() > 0 : "Insufficient arguments supplied";
        String filepath = params.get(0);

        // Retrieve the storage and the list of tasks
        storage = new TextStorage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }

        // Initialize UI components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Initialize main layout and add UI components
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Initialize the scene using the main layout
        scene = new Scene(mainLayout);

        // Set the scene of the primary stage and show the stage
        stage.setScene(scene);
        stage.show();

        // Configure the UI components
        stage.setTitle("Duke");
        stage.setResizable(false);

        // Set minimum height and width for the stage
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // Set preferred size for the main layout
        mainLayout.setPrefSize(400.0, 600.0);

        // Configure settings for the Scroll Pane
        scrollPane.setPrefSize(385, 535);

        // Allow vertical scrolling but not horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // Set the preferred height for the dialog container
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Set the preferred width for the user input and send button
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        // Anchor the components to different corners of the anchor pane
        // Position the scroll pane to be at the top
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        // Position send button to be at the bottom right
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        // Position the user input the be at the bottom left
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Add event listener to handle the user's input when the mouse is clicked
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // Add event listener to handle the user's input when 'return' key is triggered
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Add event listener to always display content at bottom of scroll panel upon changes to the dialog container
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        // Initialize new labels for both the user and duke using the content in the user input and subsequent response
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        // Retrieve the dialogs for the labels and append them to the children of the dialog container
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        // Clear the user's input
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            // Parse the input and return the output messages from executing the command joined by a newline
            Command command = Parser.parseCommand(input);
            return String.join("\n", command.execute(tasks, storage));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
