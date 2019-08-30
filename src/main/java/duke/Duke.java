package duke;

import duke.commands.Command;
import duke.directprocessor.Parser;
import duke.directprocessor.Storage;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.frontend.DialogBox;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the entrance of the whole system. It controls the logic of how the system runs.
 */
public class Duke extends Application {

    /**
     * Private instances for the back end.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Private instances for the front end.
     */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The constructor of the class, to be called at the beginning when the system is activated.
     * It initializes the storage class, user end.
     * The previously saved task list will be reloaded through the storage class and used to initialize the task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload("taskfile.txt"));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {

        // 1.  Set up the required components.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("send");
        AnchorPane mainLayer = new AnchorPane();
        mainLayer.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayer);
        stage.setScene(scene);
        stage.show();

        // 2. Formatting the window to look as expected.
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayer.setPrefSize(400.0, 600.0);
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.showWelcome()), new ImageView(duke))
        );

        // 3. Handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            if (handleUserInput()) {
                Platform.exit();
            };
        });
    }

    private boolean handleUserInput() {
        String dukeResponse;
        boolean canExit = false;
        Label userText = new Label(userInput.getText());
        try {
            Command c = Parser.parse(userInput.getText());
            dukeResponse = c.execute(tasks, ui);
            canExit = c.isExit();
        } catch (DukeException e) {
            dukeResponse = e.getMessage();
        }
        Label dukeText = new Label(dukeResponse);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        return canExit;
    }
}
