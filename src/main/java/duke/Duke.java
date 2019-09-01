package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import duke.command.CommandNotFoundException;
import duke.parser.IncorrectFileFormatException;
import duke.parser.IncorrectNumberOfArgumentsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.command.Command;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

/**
 * Represents a Duke - interactive bot.
 * Contains functions to operate the bot.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user;
    private Image duke;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(){
        String path = "https://upload.wikimedia.org/wikipedia/en/4/4d/Minions.png";

        //user = new Image("https://pokestop.io/img/pokemon/jigglypuff-256x256.png");
        //duke = new Image("https://pokestop.io/img/pokemon/jigglypuff-256x256.png");

        user = new Image(path);
        duke = new Image(path);
    }

    /**
     * Constructor for class Duke.
     *
     * @param filePath String of file path to read.
     */
    public Duke(String filePath) {
        Parser.initialize();
        storage = new Storage(filePath);

        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidPathException i) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncorrectFileFormatException | FileNotFoundException f) {
            ui.showLoadingError();
        } catch (NullPointerException n) {
            ui.showIndexError();
        }
    }

    /**
     * Runs the duke bot.
     */
    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                ui.showLine(); // show the divider line ("_______")

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();

            } catch (IndexOutOfBoundsException o) {
                ui.showIndexError();
            } catch (NullPointerException n) {
                ui.showInputError();
            } catch (CommandNotFoundException c) {
                ui.showCommandNotFoundError();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectNumberOfArgumentsException e) {
                ui.showIncorrectNumberOfArgument();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt").run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        // class javafx.scene.control.Labeled
        //helloWorld.setFont(new Font("Arial", 100.0));

        // Java FX Tutorial
        createAnchorPane(stage);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
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
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void createAnchorPane(Stage stage){
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        //Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(new DialogBox(userText.getText(), user), new DialogBox(dukeText.getText(), duke));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

}











