package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.javafx.DialogBox;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * A task list that supports several basic features:
 * 1) Addition and deletion of three types of task.
 * 2) Ability to mark tasks as done.
 * 3) Ability to search for expressions in given tasks.
 * 4) Ability to print current list of tasks.
 */
public class Duke extends Application {
    /**
     * The TaskList object which abstracts out a list of tasks.
     */
    private TaskList taskList;
    
    /**
     * The Storage object which loads and writes data to the hard drive.
     */
    private Storage storage;
    
    /**
     * The Ui object which scans input and prints feedback to the user.
     */
    private Ui ui;
    
    public static boolean isExitRunLoop;
    
    private ScrollPane scrollPane;
    
    private VBox dialogContainer;
    
    private TextField userInput;
    
    private Button sendButton;
    
    private Scene scene;
    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    
    /**
     * Initializes a Duke object.
     * The Duke constructor has no parameters due to a quirk in javafx.application.Application, which does not work
     * with a constructor with parameters. This was the best workaround I could find after 4 days of trying.
     */
    public Duke() {
        this("CurrentTaskList.txt");
    }
    
    /**
     * Initializes the Ui, Storage, and TaskList objects.
     * This method serves as a proxy for the Duke constructor method, which is left empty due to a quirk in
     * javafx.application.Application, which does not work with a constructor with parameters.
     *
     * @param filePath The file path of the hard drive location to read and write from, as a String.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadSavedList());
        } catch (IOException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }
    
    /**
     * Scans and parses commands given by the user.
     * Modifies the Tasks in the TaskList object based on the commands received by the user.
     */
    private void run() {
        ui.print(ui.showHello());
        isExitRunLoop = false;
        while (!isExitRunLoop) {
            try {
                String input = ui.getNextLine();
                Command command = Parser.parse(input);
                ui.print(command.execute(taskList, ui, storage));
            } catch (DukeException | IOException e) {
                ui.print(ui.showError(e));
            }
        }
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
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException | IOException e) {
            return ui.showError(e);
        }
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
        
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    
    public static void main(String[] args) {
        new Duke("CurrentTaskList.txt").run();
    }
}
