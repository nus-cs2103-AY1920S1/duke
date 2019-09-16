package duke;

import duke.command.Command;
import duke.command.DukeException;
import duke.data.DukeData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their to-do, deadlines, and events
 * all in one application. Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke extends Application { // handles all input and output
    private DukeData _myData;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a Duke program.
     */
    public Duke() {
        this._myData = new DukeData();
        this.ui = new Ui();
    }

    /**
     * Creates a Duke program with filePath as the path to save Duke's data.
     * @param filePath the path to save the Duke's data from user input
     */
    public Duke(String filePath) {
        this._myData = new DukeData(filePath);
        this.ui = new Ui();
    }

    /**
     * This method runs the Duke program.
     */
    private void run() {
        this.ui.showIntro();

        String userCommand;
        while (this.ui.hasNextInput() &&
                !(userCommand = this.ui.getCommand()).equals("bye")) {
            String output;
            try {
                Command cmd = Parser.parse(userCommand);
                assert cmd != null;
                output = Ui.addLines(cmd.execute(this._myData, this.ui));
                System.out.println(output);
            } catch (IOException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (DukeException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            }
        }
        this.ui.showFarewell();
    }

    /**
     * The getData() method retrieves the data that Duke has stored fdr the user.
     * @return DukeData representation of the data that has been stored
     */
    public DukeData getData() {
        return this._myData;
    }

    /**
     * This is the main method that initiates the running of the Duke program.
     * @param args Unused
     */
    public static void main(String[] args) { // handles all input and output
        Duke theDuke = new Duke();
        theDuke.run();
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
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            assert cmd != null;
            return cmd.execute(this._myData, this.ui);
        } catch (DukeException | IOException e) {
            return Ui.addLines(e.getMessage());
        }
    }
}