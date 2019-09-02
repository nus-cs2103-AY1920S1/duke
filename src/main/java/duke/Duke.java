package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

import exceptions.DukeException;

import commands.Command;

/**
 * Duke is the driver class of the program.
 * Parser, Storage, TaskList, Ui objects are instantiated here and
 * passed to Command objects in order to execute the appropriate actions.
 */
public class Duke extends Application {

    /**
     * the Storage object that reads from and writes to the file.
     */
    private Storage storage;
    /**
     * the TaskList object storing all recorded Tasks.
     */
    private TaskList tasks;
    /**
     * the Ui object dealing with user interaction.
     */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/eminem.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/lelouch2.jpeg"));

    /**
     * Duke constructor that takes in a file path and
     * instantiates Ui, Storage and TaskList objects.
     */
    public Duke() {
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The driver method where the logic of the Duke
     * program is written: parsing of user input, instantiating
     * the appropriate Command class and then executing the corresponding
     * set of actions associated with that command.
     *
     * @param fullCommand String that user typed as text
     * @return String output reply from Duke
     */
    public String run(String fullCommand) {
        String reply = "";
        boolean isExit = false;
        try {
            Command c = Parser.parse(fullCommand);
            reply = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            reply = e.getMessage();
        }
        // Save the new task list to the hard disk
        if (isExit) {
            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showSavingError(e.getMessage());
            }
        }
        return ui.showOpeningLine() + reply + ui.showClosingLine();
    }

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

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 700.0);

        scrollPane.setPrefSize(585, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(80.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Output welcome text on the GUI
        String welcomeText = ui.showWelcome();
        Label welcomeLabel = new Label(welcomeText);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeLabel, new ImageView(duke)));
        userInput.clear();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(run(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if (userInput.getText().equals("bye")) {
            Platform.exit();
        } else {
            userInput.clear();
        }
    }
}
