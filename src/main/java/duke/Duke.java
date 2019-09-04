package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.DialogBox;
import duke.tasks.Task;
import duke.utils.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * Main class that brings together all the implemented classes to execute the Duke
 * tasking logic.
 */
public class Duke {

    public static String saveFilePath = "data/savedTasks.txt";
    private Storage storage;
    private TaskList allTasks;
    private UiResponse ui;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Constructor. Will attempt to load any saved tasks specified in the
     * Duke.savedFilePath class attribute. If not saved tasks are found, then
     * an empty TaskList is initialised.
     */
    public Duke() {
        this.ui = new UiResponse();
        this.storage = new Storage(Duke.saveFilePath);
        try {
            this.allTasks = this.storage.load();
        } catch (DukeException e) {
            this.allTasks = new TaskList(new ArrayList<Task>());
        }
    }

//    /**
//     * Keeps looping through to check the console for user input. Will terminate
//     * when the user inputs "bye" to the console.
//     */
//    public static void main(String[] args) {
//        Duke.saveFilePath = "data/savedTasks.txt"; //This is meant to be readily changed.
//        Duke d = new Duke();
//        d.run();
//    }

//    /**
//     * Method to run Duke. Used to continually poll the user for input
//     * via the console input.
//     */
//    public void run() {
//        ui.printWelcomeMsg();
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(this.ui, this.storage, this.allTasks);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                //This is the highest level at which a DukeException is caught.
//                //Most DukeExceptions will be caught and handled at this level.
//                ui.printErrorMsg(e);
//            } catch (NoSuchElementException e) {
//                ui.printSentence("Please enter a command!");
//            }
//        }
//
//        ui.printExitMsg();
//    }

//    @Override
//    public void start(Stage stage) {
//        //The container for the content of the chat to scroll.
//        this.scrollPane = new ScrollPane();
//        this.dialogContainer = new VBox();
//        this.scrollPane.setContent(this.dialogContainer);
//
//        this.userInput = new TextField();
//        this.sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);
//
//        this.scene = new Scene(mainLayout);
//
//        stage.setScene(this.scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(800.0);
//
//        mainLayout.setPrefSize(800.0, 600.0);
//
//        this.scrollPane.setPrefSize(795, 535);
//        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        this.scrollPane.setVvalue(1.0);
//        this.scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        this.userInput.setPrefWidth(725.0);
//
//        this.sendButton.setPrefWidth(70.0);
//
//        AnchorPane.setTopAnchor(this.scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
//        AnchorPane.setRightAnchor(this.sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(this.userInput , 1.0);
//        AnchorPane.setBottomAnchor(this.userInput, 1.0);
//
//        this.sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        this.userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        this.dialogContainer.heightProperty().addListener((observable) -> this.scrollPane.setVvalue(1.0));
//    }

//    /**
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        try {
//            Label userText = new Label(this.userInput.getText());
//            Label dukeText = new Label(getResponse(this.userInput.getText()));
//            this.dialogContainer.getChildren().addAll(
//                    DialogBox.getUserDialog(userText, new ImageView(this.user)),
//                    DialogBox.getDukeDialog(dukeText, new ImageView(this.duke))
//            );
//            this.userInput.clear();
//        } catch (DukeException e) {
//            //This is the highest level at which a DukeException is caught.
//            //Most DukeExceptions will be caught and handled at this level.
//            handleError("Sorry, I didn't understand your command!");
//        } catch (NoSuchElementException e) {
//            handleError("Sorry, please enter a command!");
//        }
//    }
//
    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) throws DukeException, NoSuchElementException{
        Command c = Parser.parse(input);
        return c.execute(this.ui, this.storage, this.allTasks);
    }
}
