package duke;

import duke.exception.DukeException;
import duke.gui.DialogBox;
import duke.ui.Ui;
import java.io.IOException;
import java.util.Scanner;
import duke.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Main class duke to start the programme.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Saved savedFile;
    private Scanner scan;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a Duke object.
     * Initialise the storage, task list and ui objects.
     */
    public Duke() {
        savedFile = new Saved("src/main/java/data.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(savedFile.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     * Saves updated tasks back into the local file
     *
     * @throws IOException filePath is inaccessible or cannot be found
     */
    public void run() throws IOException {
        String input;
        Command cmd;

        ui.printIntro();

        Boolean isBye = false;
        while (!isBye) {
            try {
                input = ui.scanCmd();
                ui.printLine();
                cmd = Parser.parse(input);
                cmd.execute(tasks, ui);
                isBye = cmd.isBye();
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
            ui.printLine();
        }
        savedFile.saveToFile(tasks.getTaskArrayList());

    }

    /**
     * Required main method.
     *
     * @param args command line arguments
     * @throws IOException local file is inaccessible or cannot be found
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Gets response from the user.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}