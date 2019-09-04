package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 * Main class that brings together all the implemented classes to execute the Duke
 * tasking logic.
 */
public class Duke extends Application {

    public static String saveFilePath = "data/savedTasks.txt";
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructor. Will attempt to load any saved tasks specified in the
     * Duke.savedFilePath class attribute. If not saved tasks are found, then
     * an empty TaskList is initialised.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.saveFilePath);
        try {
            this.allTasks = this.storage.load();
        } catch (DukeException e) {
            ui.printErrorMsg(e);
            this.allTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Keeps looping through to check the console for user input. Will terminate
     * when the user inputs "bye" to the console.
     */
    public static void main(String[] args) {
        Duke.saveFilePath = "data/savedTasks.txt"; //This is meant to be readily changed.
        Duke d = new Duke();
        d.run();
    }

    /**
     * Method to run Duke. Used to continually poll the user for input
     * via the console input.
     */
    public void run() {
        ui.printWelcomeMsg();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.ui, this.storage, this.allTasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                //This is the highest level at which a DukeException is caught.
                //Most DukeExceptions will be caught and handled at this level.
                ui.printErrorMsg(e);
            } catch (NoSuchElementException e) {
                ui.printSentence("Please enter a command!");
            }
        }

        ui.printExitMsg();
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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




    }
}
