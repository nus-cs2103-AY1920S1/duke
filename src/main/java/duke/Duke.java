package duke;

import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Parser;

import duke.exceptions.DukeDuplicateException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalDescriptionException;
import duke.exceptions.DukeIllegalInputException;

import duke.visual.DialogBox;

import javafx.application.Application;
import javafx.application.Platform;

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
 * The main class of Duke chat bot.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * The constructor of the Duke class, pass string value to the variable, load the file from hard disk and create
     * a new file if not existed.
     *
     * @param filePath The store location of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            create();
        }
    }

    public Duke() {
        this("/data/Tasks.txt");
    }

    /**
     * To create a new folder and new file if not existed and set a new empty task list.
     */
    private void create() {
        storage.createFolder();
        storage.createFile();
        tasks = new TaskList();
    }

    /**
     * To greet the users.
     */
    private void greet() {
        System.out.println(ui.greet());
    }

    /**
     * To execute the main function of the program and print the exception if encountered.
     */
    public void run() {
        try {
            ui.scan();
        } catch (DukeIllegalInputException | DukeIllegalDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        //Set up the required components.
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
        //Formatting the window to look as expected.
        formatWindow(stage, mainLayout);
        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void formatWindow(Stage stage, AnchorPane mainLayout) {
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.greet()), new ImageView(duke)));
    }

    private void handleUserInput() {
        String response;
        Label userText = new Label(userInput.getText());
        try {
            String[] action = userInput.getText().split(" ", 2);
            Parser parser = new Parser(action);
            response = parser.parse();
        } catch (DukeIllegalDescriptionException | DukeDuplicateException e) {
            response = e.getMessage();
        } catch (IllegalArgumentException e) {
            response = new DukeIllegalInputException().getMessage();
        }
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (!Ui.getFlag()) {
            Platform.exit();
        }
    }
}
