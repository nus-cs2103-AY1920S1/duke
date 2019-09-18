package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

import duke.gui.DialogBox;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents core of the Duke program.
 * Acts as a planner for the Duke user by allowing for CRUD functionality on Tasks specified by user.
 */
public class Duke extends Application {

    //region VARIABLES
    private static String LIST_PATH = "src/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    //endregion

    /**
     * Creates an object of type Duke and performs setup for program.
     */
    public Duke() {
        storage = new Storage(LIST_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
            ui.showError(ex.getMessage());
        }
    }

    /**
     * Main driver of Duke program. Uses list of tasks to store user specified activities.
     * Able to perform CRUD functionality when queried by user.
     *
     */
    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null && fullCommand != "" : "Critical Error from User Input";
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        AnchorPane mainLayout = new AnchorPane();
        initControls(stage, mainLayout);
        styleControls(stage, mainLayout);

        Label greetings = new Label(ui.greetings());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetings, new ImageView(duke))
        );

        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });
        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        if (userText.getText().equals("bye")) {
            try {
                storage.writeListToFile(tasks);
            } catch (DukeException e) {

            }
            stage.close();
        }
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    /**
     * Gets response of Duke program to user request
     */
    private String getResponse(String input) {
        String output = "";
        try {
            if (input != null && input != "") {
                Command c = Parser.parse(input);
                output = c.execute(tasks, ui, storage);
            }
        } catch (DukeException ex) {
            output = ex.getMessage();
        }
        return output;
    }

    private void initControls(Stage stage, AnchorPane mainLayout) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private void styleControls(Stage stage, AnchorPane mainLayout) {
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }



}