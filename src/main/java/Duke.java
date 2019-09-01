import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.stage.Stage;

import slave.command.Command;
import slave.command.CommandType;

import slave.elements.Parser;
import slave.elements.Storage;
import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

/**
 * <h1>
 *     Welcome to Slave.
 * </h1>
 * <p>
 *     This Slave Program is a productivity application which helps
 * you to store and load tasks as well as letting you mark them
 * as done as you go about your daily routine!
 * </p>
 *
 * @author Kalsyc
 * @version 1.0
 * @since 28 August 2019
 *
 */
public class Duke extends Application {

    private Ui ui;
    private TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/doge.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/kermit.png"));

    /**
     * Drives the application. Main method.
     *
     * @param args Placeholder
     * @throws DukeException Throws any exception that arises with running the application
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    /**
     * Constructor which initialises storage and taskList.
     *
     * @throws DukeException In case storage cannot be loaded
     */
    public Duke() throws DukeException {
        this.ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(storage.load(), storage);
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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Runs the logic of the application. It takes in user input
     * and parses the command before determining what type of command and execute
     * the command accordingly.
     */
    private void run() {
        this.ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

}
