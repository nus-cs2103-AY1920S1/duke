package duke;

import duke.commands.Command;
import duke.directprocessor.Parser;
import duke.directprocessor.Storage;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;

import java.io.IOException;

import duke.frontend.DialogBox;
import javafx.application.Application;
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

    /**
     * Do not use this constructor, it is written simply for executing javafx.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    /**
     * The constructor of the class, to be called at the beginning when the system is activated.
     * It initializes the storage class, user end.
     * The previously saved task list will be reloaded through the storage class and used to initialize the task list.
     * @param fileName The file from which the precious task list is reloaded.
     */
    public Duke(String fileName) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.reload(fileName));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This is the logic of how the system runs.
     * It first show welcome message to the user, loops the following process before the exit command is given.
     * 1. The ui takes the user's input.
     * 2. The ui draws a line.
     * 3. The Parser processes the user's input and generates the corresponding command.
     * 4. Executes the command and decide if it is an exit command.
     * 5. The ui draws another line
     * 6. The system ends of the command is an exit command, or go back to step 1 otherwise.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.takeCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

    /**
     * The main method of class, as well as the entrance method of the whole system.
     * @param args Required by a valid main method.
     */
    public static void main(String[] args) {
        new Duke("taskfile.txt").run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // First,  Set up the required components.
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
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
}
