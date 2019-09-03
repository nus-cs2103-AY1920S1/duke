package ui;

import commands.Command;
import parser.Parser;
import storage.Storage;
import util.TaskList;

import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Encapsulates attributes and behaviour of ui.Duke, a personal assistant chatbot.
 *
 * <p> Duke manages a user's list of tasks. It can store, add to, remove from, find,
 * or modify the contents of the list. User input must come either in the form of
 * commands 'list', 'bye' 'done (task index)', 'delete (task index)' or storage
 * requests beginning with the type of task to be stored. Duke supports three
 * types of tasks - todos, deadlines, and events. Deadlines and events need to
 * be supplied with additional date or time information, which is parsed by
 * Duke using Java's SimpleDateFormat library. The input format for
 * todos is 'to-do (task description)' and that for deadlines and events is
 * '(task type) (task description) / (day/month/year hh:mm)'. Deviating from this
 * input format results in Duke supplying error messages to the user. Duke
 * currently runs a simple graphic user interface supported by JavaFX. </p>
 *
 * @author atharvjoshi
 * @contributors j-lum, damithc
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Duke extends Application {

    /** Handler for loading from and writing to hard disk. */
    private Storage storage;

    /** The list of tasks. */
    private TaskList tasks;

    /** Handler for dealing with user interactions. */
    private Ui ui;

    /** flag to indicate if ui.Duke is listening to commands from the user. */
    private boolean isListening;

    /** A scroll pane for the Duke GUI. */
    private ScrollPane scrollPane;

    /** A container to display dialogs in Duke GUI. */
    private VBox dialogContainer;

    /** A field to enter user input in Duke GUI. */
    private TextField userInput;

    /** A button to send input in Duke GUI. */
    private Button sendButton;

    /** The user's display picture in the GUI. */
    private Image user = new Image(this.getClass()
            .getResourceAsStream("/images/user.png"));

    /** Duke's display picture in the GUI. */
    private Image duke = new Image(this.getClass()
            .getResourceAsStream("/images/duke.png"));

    /**
     * Initialises Duke with a user-interface, storage, and task
     * handler.
     *
     */
    public Duke() {
        ui = new Ui();

        String filePath = "data/tasks.txt"; // saved task list file
        storage = new Storage(filePath);

        this.isListening = true;
        try {
            this.tasks = new TaskList();
            storage.loadToList(tasks);
        } catch (IOException | ParseException exception) {
            ui.showStartupError();
            this.tasks = new TaskList();
        }
    }

    /**
     * The start method for the Duke application.
     * @param stage stage on which scenes run.
     */
    @Override
    public void start(Stage stage) {
        // code reused from JavaFX tutorials by CS2103 teaching dept
        this.setupStage(stage);

        // handle user input based on user interactions.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Initialise the stage elements and layout for the Duke GUI.
     *
     * @param stage the stage to be set up.
     */
    private void setupStage(Stage stage) {
        // code reused from JavaFX tutorials by CS2103 teaching dept

        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // show welcome message
        Label welcomeText = new Label(ui.getWelcomeMessage());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeText, new ImageView(duke)));
    }

    /**
     * Handler for user input which runs user commands and presents
     * Duke's responses.
     */
    private void handleUserInput() {
        // code adapted from JavaFX tutorials by CS2103 teaching dept
        String fullCommand = userInput.getText(); // get command
        String dukeResponse = getResponse(fullCommand); // run command

        // create labels
        Label userText = new Label(fullCommand);
        Label dukeText = new Label(dukeResponse);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Run the command entered by the user and return a string corresponding to
     * Duke's response.
     *
     * @return string representing Duke's response to given user input.
     */
    private String getResponse(String inputString) {
        // code adapted from JavaFX tutorials by CS2103 teaching dept
        while (this.isListening) {
            try {
                Command command = Parser.parse(inputString);
                String responseString = command.execute(tasks, ui, storage);
                this.isListening = !command.getIsExit();
                return responseString;
            } catch (IllegalArgumentException exceptionOne) {
                return ui.showInvalidCommandError();
            } catch (IndexOutOfBoundsException exceptionTwo) {
                return ui.showInvalidFormattingError();
            } catch (IOException exceptionThree) {
                return ui.showStartupError();
            } catch (ParseException exceptionFour) {
                return ui.showInvalidDateTimeFormattingError();
            }
        }

        // user has requested to restart duke.
        if (inputString.equalsIgnoreCase("restart")) {
            this.isListening = true;
            return ui.getWelcomeMessage();
        }

        // let user know that a recent instance of Duke has stopped running
        // and steps to take next.
        return "Duke has already stopped running! "
                + "Type 'restart' to talk to Duke again, or simply close the window!!!";
    }
}