import cs2103t.duke.command.Command;
import cs2103t.duke.exception.DukeException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

import javafx.application.Application;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Acts as a chatbot to remember your tasks at hand.
 * There will be three kinds of tasks: Todo, Deadline, Event.
 * Valid inputs include:
 * {@code list} to list the tasks it remembered so far;
 * {@code todo some todo description} to save a new Todo task with description "some todo description";
 * {@code deadline something /by dd/MM/yyyy HHmm} to save a new Deadline task with description "something"
 * and deadline dd/MM/yyyy HHmm;
 * {@code event some event /at datetime} to save a new Event task with description "some event" held at datetime;
 * {@code done id} to mark the (id)th task as completed;
 * {@code delete id} to remove the (id)th task from list of tasks to remember;
 * {@code bye} or {@code exit} to exit from duke.
 * Duke will save the tasks in a file ("./data/tasks.txt") and read from it.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/walle.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/eve.png"));

    /** Handles file reading and writing. */
    private Storage storage;
    /** Handles direct changes to list of tasks. */
    private TaskList tasks;
    /** Handles system i/o in duke format. */
    private Ui ui;

    private String dataFilepath = "./data/tasks.txt";

    /**
     * Constructs Duke object to be called by Application launcher, javafx.
     * Storage filepath used default ./data/tasks.txt.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(dataFilepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke chatbot that reads from and writes to file located at datafilepath.
     * @param dataFilepath file path to read and write data.
     */
    public Duke(String dataFilepath) {
        this.ui = new Ui();
        this.storage = new Storage(dataFilepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke chatbot.
     */
    /*
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.toString());
            }
        }
    }
     */

    public String getResponse(String fullCommand) {
        ///*
        String res;
        try {
            Command c = Parser.parse(fullCommand);
            res = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            res = e.toString();
        }
        if (res == null) {
            res = "cannot work sia";
        }
        return res;
        //*/
        //return "Duke heard: " + fullCommand;
    }

    public static void main(String[] args) {
    //    new Duke("./data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //step 1: setting up required components
        //container for content of chat to scroll
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

        //step 2: formatting the window to look as expected
        stage.setTitle("Grand Duke of GUIs");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //need to import javafx.scene.layout.Region for this
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //step 3: add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            /*CLICKING SEND BUTTON*/
            //System.out.println("sendbutton clicked!");
            handleUserInput();
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
        });

        userInput.setOnAction((event) -> {
            /*PRESSING ENTER TO SEND MSGS INSTEAD*/
            //System.out.println("userinput clicked?");
            handleUserInput();
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
        });

        //scroll down to the end (last msg) every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        Label dukeText = new Label(this.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                //new DialogBox(userText, new ImageView(user)),
                //new DialogBox(dukeText, new ImageView(duke))
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        //System.out.println(userInput.getText());
        userInput.clear();
    }
}
